/**
 * Novelan Lux_WS adapter. This package is the only code that understands
 * the heat pump's WebSocket frames and German content-block names
 * ({@code Temperaturen}, {@code Anlagenstatus}).
 *
 * <p>The client implements {@code HeatGenerator} so the core can read the
 * current setpoint. Mapping from those frames into {@code HeatpumpSnapshot}
 * stays here. If the protocol changes, this package changes and the
 * automation does not.
 */
package de.saki.enerflow.adapter.heatpump.novelan;
