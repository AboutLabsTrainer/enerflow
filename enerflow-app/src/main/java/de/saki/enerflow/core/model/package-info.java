/**
 * Ports: the interfaces the core programs against.
 *
 * <p>A port is a promise, not a device. {@link de.saki.enerflow.core.model.EnergySource}
 * says "tell me how many watts the roof is producing." It does not say
 * "call the sonnen REST API." The sonnen adapter and the mock adapter both
 * keep that promise. {@code EnergyManagerService} asks the port, so swapping
 * hardware never changes the decision logic.
 *
 * <p>New vendor = new class in {@code adapter} that implements one of these
 * interfaces. This package stays as it is.
 */
package de.saki.enerflow.core.model;
