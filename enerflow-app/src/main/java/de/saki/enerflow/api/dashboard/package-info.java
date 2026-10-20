/**
 * Dashboard driving adapter. The browser loads a Thymeleaf page that is
 * almost empty, then polls {@code GET /api/dashboard/status} and paints the
 * DOM in the browser.
 *
 * <p>{@code DashboardService} only reads repositories. It does not ask the
 * heat pump for a live value. What you see is the latest row the schedulers
 * (or the mock snapshot writer) have already stored. That is why the mock
 * profile must persist snapshots: without those rows this package has
 * nothing to show.
 */
package de.saki.enerflow.api.dashboard;
