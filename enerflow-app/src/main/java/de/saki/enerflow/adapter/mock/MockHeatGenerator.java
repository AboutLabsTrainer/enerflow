package de.saki.enerflow.adapter.mock;

import de.saki.enerflow.config.HeatpumpProperties;
import de.saki.enerflow.core.model.HeatGenerator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Mock implementation of {@link HeatGenerator} for local development without a
 * real heat pump. Tracks a simulated hot-water temperature that drifts toward
 * the configured setpoint at 1\u00B0C per call, so the RAISE/RESET automation
 * can be observed in the dashboard and control log.
 */
@Component
@Profile("mock")
public class MockHeatGenerator implements HeatGenerator {

    private final HeatpumpProperties properties;

    private double setpointCelsius = 47.0;
    private double actualTemperatureCelsius = 47.0;

    public MockHeatGenerator(HeatpumpProperties properties) {
        this.properties = properties;
    }

    @Override
    public double getHotWaterTemperatureCelsius() {
        driftTowardSetpoint();
        return actualTemperatureCelsius;
    }

    @Override
    public double getHotWaterSetpointCelsius() {
        return setpointCelsius;
    }

    @Override
    public void setHotWaterSetpoint(double targetTemperatureCelsius) {
        double min = properties.getMinSetpointCelsius();
        double max = properties.getMaxSetpointCelsius();
        if (targetTemperatureCelsius < min || targetTemperatureCelsius > max) {
            throw new IllegalArgumentException(
                    "Setpoint %.1f \u00B0C is outside the allowed range [%.1f – %.1f \u00B0C]".formatted(
                            targetTemperatureCelsius, min, max));
        }
        this.setpointCelsius = targetTemperatureCelsius;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    /**
     * Moves the actual temperature one degree closer to the setpoint on each
     * call, simulating thermal inertia of a hot-water tank.
     */
    private void driftTowardSetpoint() {
        double diff = setpointCelsius - actualTemperatureCelsius;
        if (Math.abs(diff) > 0.5) {
            actualTemperatureCelsius += diff > 0 ? 1.0 : -1.0;
        } else {
            actualTemperatureCelsius = setpointCelsius;
        }
    }
}