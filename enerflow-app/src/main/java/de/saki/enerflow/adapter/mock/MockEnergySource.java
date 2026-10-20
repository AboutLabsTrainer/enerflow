package de.saki.enerflow.adapter.mock;

import de.saki.enerflow.core.model.EnergySource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

/**
 * Driven adapter, mock profile only. Same port as the sonnen PV reading,
 * fed by a 60-minute clock that starts when the process starts. The core
 * cannot tell this from a roof. Do not import this class from core.service.
 *
 * Mock implementation of {@link EnergySource} for local development without a
 * sonnenBatterie or PV system. Production and surplus follow a simulated
 * 60-minute day/night cycle based on time since application startup, so the
 * automation logic can be exercised regardless of the real time of day.
 *
 * <p>Cycle:</p>
 * <ul>
 *   <li>Minutes 0–30: Solar production ramps up, surplus exceeds 300 W.</li>
 *   <li>Minutes 30–60: Production ramps back down to zero (night).</li>
 * </ul>
 */
@Component
@Profile("mock")
public class MockEnergySource implements EnergySource {

    private static final int PEAK_PRODUCTION_WATTS = 5700; // 5.7 kWp
    private static final int BASE_CONSUMPTION_WATTS = 400;

    private final Instant startTime = Instant.now();

    @Override
    public int getProductionWatts() {
        long minutes = Duration.between(startTime, Instant.now()).toMinutes();
        // 60-minute simulated day cycle, peak at minute 15
        double cycle = (minutes % 60) / 60.0 * 2.0 * Math.PI;
        double production = PEAK_PRODUCTION_WATTS * Math.max(0.0, Math.sin(cycle));
        return (int) production;
    }

    /**
     * Household base load used by the simulated surplus calculation.
     * Exposed so mock snapshots can show the same consumption the surplus is based on.
     */
    public int getConsumptionWatts() {
        return BASE_CONSUMPTION_WATTS;
    }

    @Override
    public int getSurplusWatts() {
        int production = getProductionWatts();
        int surplus = production - getConsumptionWatts();
        return Math.max(0, surplus);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}