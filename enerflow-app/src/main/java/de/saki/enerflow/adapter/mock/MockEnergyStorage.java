package de.saki.enerflow.adapter.mock;

import de.saki.enerflow.core.model.EnergyStorage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

/**
 * Mock implementation of {@link EnergyStorage} for local development without a
 * sonnenBatterie. State of charge starts at 30 % and charges by 10 % per
 * minute until it reaches 100 %, simulating a battery that fills up during
 * the day.
 */
@Component
@Profile("mock")
public class MockEnergyStorage implements EnergyStorage {

    private static final int STARTING_SOC_PERCENT = 30;
    private static final int CHARGE_PERCENT_PER_MINUTE = 10;

    private final Instant startTime = Instant.now();

    @Override
    public int getStateOfChargePercent() {
        long minutes = Duration.between(startTime, Instant.now()).toMinutes();
        int soc = STARTING_SOC_PERCENT + (int) (minutes * CHARGE_PERCENT_PER_MINUTE);
        return Math.min(100, Math.max(0, soc));
    }

    @Override
    public boolean isCharging() {
        return getStateOfChargePercent() < 100;
    }

    @Override
    public boolean isDischarging() {
        return false;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}