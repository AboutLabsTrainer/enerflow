/**
 * Driving adapter for the electricity price. A homeowner can read and change
 * the cents-per-kilowatt-hour value. Every change is appended to
 * {@code electricity_price_change_log} so the history is not lost when the
 * current price is overwritten.
 */
package de.saki.enerflow.api.config;
