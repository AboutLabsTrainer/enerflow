/**
 * Simulated hardware, active only when the Spring profile {@code mock} is on.
 *
 * <p>These classes implement the same ports as the real adapters
 * ({@code EnergySource}, {@code EnergyStorage}, {@code HeatGenerator}).
 * Spring loads either the real beans ({@code @Profile("!mock")}) or these
 * ones ({@code @Profile("mock")}), never both. The decision service is
 * unchanged, which is the point of the exercise: you can watch RAISE and
 * RESET without a house full of devices.
 *
 * <p>{@code MockAdapterConfig} publishes the fake heat pump under the same
 * bean names the real reader and writer use. Qualifiers in
 * {@code EnergyManagerService} then resolve either way.
 */
package de.saki.enerflow.adapter.mock;
