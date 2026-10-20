/**
 * Time is an incoming adapter. Nothing in the core has a clock loop.
 * These classes wake up every polling interval and call one service method.
 *
 * <p>Hardware polling is off in the {@code mock} profile
 * ({@code @Profile("!test & !mock")}). The energy manager stays on
 * ({@code @Profile("!test")}) so the simulated devices are still judged by
 * the real decision logic. The mock package writes the snapshot rows the
 * hardware schedulers would have written.
 */
package de.saki.enerflow.scheduler;
