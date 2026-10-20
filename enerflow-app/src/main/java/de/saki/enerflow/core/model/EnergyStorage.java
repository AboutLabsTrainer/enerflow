package de.saki.enerflow.core.model;

/**
 * Port for the home battery. The automation needs state of charge and
 * whether the device is reachable. Power-flow details that the decision
 * does not use stay out of the interface, even if the vendor sends them.
 *
 * Represents an electrical energy storage device (e.g. home battery).
 * Implementations provide state-of-charge and power flow data.
 *
 * @author saki
 */
public interface EnergyStorage {

    /**
     *
     * @return Returns the current state of charge in percent (0–100).
     */
    int getStateOfChargePercent();


    /**
     *
     * @return true if the battery is currently charging.
     */
    boolean isCharging();

    /**
     *
     * @return true if the battery is currently discharging.
     */
    boolean isDischarging();

    /**
     *
     * @return true if this storage device is reachable and delivering valid data.
     */
    boolean isAvailable();

}
