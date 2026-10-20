/**
 * Application services: the use cases of EnerFlow.
 *
 * <p>This is the inside of the hexagon. {@code EnergyManagerService} is the
 * automation. The other services load configuration, hash passwords, turn a
 * temperature rise into kilowatt-hours, and delete old snapshots. They call
 * ports ({@code core.model}) and repository interfaces ({@code core.repository}).
 * They do not open sockets or build JSON for a vendor.
 *
 * <p>Adapters call into this package. This package does not call adapters.
 * When a service needs a heat pump, it receives a {@code HeatGenerator},
 * which might be the real device or the mock.
 */
package de.saki.enerflow.core.service;
