/**
 * Spring wiring and typed configuration. Classes here bind
 * {@code application.yaml} (and the environment variables it references) to
 * Java objects, and they build the few beans that are not just
 * {@code @Component} on an adapter.
 *
 * <p>{@code HeatpumpConfig} is profile-gated with {@code !test & !mock}.
 * In those profiles nobody should open a WebSocket to a heat pump that is
 * not there. The mock profile supplies the same bean names from
 * {@code adapter.mock} instead.
 */
package de.saki.enerflow.config;
