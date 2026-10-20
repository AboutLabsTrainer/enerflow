/**
 * myUplink cloud adapter. Writing a setpoint is a REST call with an OAuth2
 * client-credentials token, not a WebSocket message. The firmware of the
 * Helox 5 accepts the domestic hot-water target as parameter {@code 5105}.
 *
 * <p>Reads are intentionally absent. Monitoring stays on the Novelan
 * WebSocket. This class still implements {@code HeatGenerator} so the core
 * has one type for "the thing that can change the setpoint."
 */
package de.saki.enerflow.adapter.heatpump.myuplink;
