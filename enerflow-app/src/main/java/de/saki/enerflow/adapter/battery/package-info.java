/**
 * Battery and PV adapters. On this installation both measurements come from
 * the sonnenBatterie: the battery reports its own charge and the inverter's
 * PV production in one status payload. The core still sees two ports
 * ({@code EnergyStorage} and {@code EnergySource}), so a later PV inverter
 * can replace only the source side.
 */
package de.saki.enerflow.adapter.battery;
