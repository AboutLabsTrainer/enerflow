package de.saki.enerflow.api.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Driving adapter, HTML half of the dashboard. It returns a template name
 * and no measurements. The page is a shell. Vanilla JS fills it. Keeping
 * that split means a status change does not re-render the whole page.
 *
 * MVC controller serving the Thymeleaf dashboard page.
 * Distinct from {@link DashboardController} which serves the REST JSON endpoint.
 *
 * The actual data is NOT server-side rendered here – the HTML page loads first,
 * then Vanilla JS polls GET /api/dashboard/status every 10 seconds and updates
 * the DOM directly. This keeps the page responsive and avoids full reloads.
 *
 * @author saki
 */
@Controller
public class DashboardPageController {

    /**
     * Serves the dashboard HTML page.
     * Returns the Thymeleaf template name "dashboard",
     * which resolves to src/main/resources/templates/dashboard.html.
     *
     * @return Thymeleaf template name
     */
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
