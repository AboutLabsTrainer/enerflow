package de.saki.enerflow.adapter.mock;

import de.saki.enerflow.core.model.EnergySource;
import de.saki.enerflow.core.model.EnergyStorage;
import de.saki.enerflow.core.model.HeatGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Spring configuration for the mock profile. Replaces all hardware adapters
 * with simulated implementations so EnerFlow can run locally without a
 * Novelan heat pump, sonnenBatterie, or PV system.
 *
 * <p>Beans are named exactly as the real adapters are named
 * ({@code novelanHeatpumpClient}, {@code myUplinkRestAdapter}) so that
 * {@link de.saki.enerflow.core.service.EnergyManagerService} can inject them
 * via {@code @Qualifier} without any code changes.
 */
@Configuration
@Profile("mock")
public class MockAdapterConfig {

    /**
     * Exposes the single {@link MockHeatGenerator} instance under the name
     * expected by {@code EnergyManagerService} for the heat-pump reader.
     */
    @Bean(name = "novelanHeatpumpClient")
    public HeatGenerator novelanHeatpumpClient(MockHeatGenerator mockHeatGenerator) {
        return mockHeatGenerator;
    }

    /**
     * Exposes the same {@link MockHeatGenerator} instance under the name
     * expected by {@code EnergyManagerService} for the heat-pump writer.
     */
    @Bean(name = "myUplinkRestAdapter")
    public HeatGenerator myUplinkRestAdapter(MockHeatGenerator mockHeatGenerator) {
        return mockHeatGenerator;
    }
}