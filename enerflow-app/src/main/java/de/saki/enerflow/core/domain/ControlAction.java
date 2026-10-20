package de.saki.enerflow.core.domain;

/**
 * The three kinds of setpoint change the audit log can record. RAISE and
 * RESET are the automation. MANUAL is reserved for a human override.
 * Storing the enum name, not a number, keeps old rows readable.
 *
 * Type of control action recorded in {@link ControlLog}.
 * RAISE/RESET are performed automatically by EnergyManagerService,
 * MANUAL is a direct user override (relevant once AP3.3 is in place).
 *
 * @author saki
 */
public enum ControlAction {
    RAISE,
    RESET,
    MANUAL
}
