/**
 * HTTP API for the dashboard and for settings that are not device commands.
 *
 * <p>Same role as {@code adapter.web.dto}: a driving adapter. It was split
 * out by feature (dashboard vs. electricity price) rather than by the word
 * "dto". Both packages stop at the core. Neither one imports a vendor client.
 */
package de.saki.enerflow.api;
