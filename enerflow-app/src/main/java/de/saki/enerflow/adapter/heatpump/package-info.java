/**
 * Heat-pump adapters. Two vendors appear here because one physical pump
 * cannot do both jobs through one API.
 *
 * <p>{@code novelan} reads temperatures over a local WebSocket (Lux_WS).
 * {@code myuplink} writes the hot-water setpoint through the cloud REST API.
 * Both implement {@link de.saki.enerflow.core.model.HeatGenerator}. The core
 * asks for a reader and a writer by bean name and never imports these packages.
 */
package de.saki.enerflow.adapter.heatpump;
