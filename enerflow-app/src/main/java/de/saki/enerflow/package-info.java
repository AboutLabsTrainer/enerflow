/**
 * Application entry point. Start reading here, then follow the packages inward.
 *
 * <p>EnerFlow is arranged as a hexagon. The {@code core} package is the
 * middle: it decides when surplus solar power should heat water, and it
 * knows nothing about WebSockets, REST vendors, or HTTP. {@code adapter}
 * packages sit on the outside and translate between that decision and the
 * real world (a heat pump, a battery, a browser). {@code scheduler} wakes
 * the decision on a timer. {@code config} wires Spring, security, and the
 * values from {@code application.yaml}.
 *
 * <p>The rule that makes the split useful: a dependency arrow points inward.
 * An adapter may import a core interface. The core must not import an adapter.
 */
package de.saki.enerflow;
