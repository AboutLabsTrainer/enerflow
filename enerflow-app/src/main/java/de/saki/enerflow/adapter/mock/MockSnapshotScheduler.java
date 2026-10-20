package de.saki.enerflow.adapter.mock;

import de.saki.enerflow.core.domain.BatterySnapshot;
import de.saki.enerflow.core.domain.HeatpumpSnapshot;
import de.saki.enerflow.core.repository.BatterySnapshotRepository;
import de.saki.enerflow.core.repository.HeatpumpSnapshotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Persists simulated battery and heat-pump readings while the mock profile is
 * active. The dashboard reads the latest snapshot rows, and the hardware
 * polling schedulers are disabled in this profile, so without this writer the
 * status endpoint has nothing to show.
 */
@Component
@Profile("mock")
public class MockSnapshotScheduler implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(MockSnapshotScheduler.class);

    private final MockEnergySource energySource;
    private final MockEnergyStorage energyStorage;
    private final MockHeatGenerator heatGenerator;
    private final BatterySnapshotRepository batterySnapshotRepository;
    private final HeatpumpSnapshotRepository heatpumpSnapshotRepository;

    public MockSnapshotScheduler(MockEnergySource energySource,
                                 MockEnergyStorage energyStorage,
                                 MockHeatGenerator heatGenerator,
                                 BatterySnapshotRepository batterySnapshotRepository,
                                 HeatpumpSnapshotRepository heatpumpSnapshotRepository) {
        this.energySource = energySource;
        this.energyStorage = energyStorage;
        this.heatGenerator = heatGenerator;
        this.batterySnapshotRepository = batterySnapshotRepository;
        this.heatpumpSnapshotRepository = heatpumpSnapshotRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        capture();
    }

    @Scheduled(fixedRateString = "${enerflow.evaluation-interval-ms}")
    public void capture() {
        try {
            saveBatterySnapshot();
            saveHeatpumpSnapshot();
        } catch (Exception e) {
            log.error("Failed to capture mock snapshots: {}", e.getMessage(), e);
        }
    }

    private void saveBatterySnapshot() {
        int productionWatts = energySource.getProductionWatts();
        int consumptionWatts = energySource.getConsumptionWatts();
        int surplusWatts = energySource.getSurplusWatts();
        boolean charging = energyStorage.isCharging() && surplusWatts > 0;
        // Positive battery power is energy flowing into the battery. The dashboard
        // derives grid exchange as production - consumption - battery power.
        int batteryPowerWatts = charging ? surplusWatts : 0;

        BatterySnapshot snapshot = new BatterySnapshot();
        snapshot.setTimestamp(LocalDateTime.now());
        snapshot.setUsableSocPercent(energyStorage.getStateOfChargePercent());
        snapshot.setProductionWatts(productionWatts);
        snapshot.setConsumptionWatts(consumptionWatts);
        snapshot.setBatteryPowerWatts(batteryPowerWatts);
        snapshot.setBatteryCharging(charging);
        snapshot.setBatteryDischarging(energyStorage.isDischarging());
        snapshot.setSurplusWatts(surplusWatts);
        batterySnapshotRepository.save(snapshot);
    }

    private void saveHeatpumpSnapshot() {
        double actual = heatGenerator.getHotWaterTemperatureCelsius();
        double setpoint = heatGenerator.getHotWaterSetpointCelsius();

        HeatpumpSnapshot snapshot = new HeatpumpSnapshot();
        snapshot.setTimestamp(LocalDateTime.now());
        snapshot.setWarmwasserIst(actual);
        snapshot.setWarmwasserSoll(setpoint);
        snapshot.setBetriebszustand(actual + 0.5 < setpoint ? "Warmwasser" : "Bereit");
        heatpumpSnapshotRepository.save(snapshot);
    }
}
