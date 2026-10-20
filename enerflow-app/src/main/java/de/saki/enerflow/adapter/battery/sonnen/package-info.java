/**
 * sonnenBatterie local REST adapter. {@code GET /api/v2/status} is the only
 * vendor call in this package. One class implements both energy ports because
 * the device returns PV production and battery charge together.
 *
 * <p>The snapshot service copies that reading into {@code battery_snapshot}
 * for the dashboard. It does not decide whether to raise the heat-pump
 * setpoint — that decision lives in {@code EnergyManagerService}.
 */
package de.saki.enerflow.adapter.battery.sonnen;
