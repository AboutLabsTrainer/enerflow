package de.saki.enerflow.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Typed view of the enerflow section in application.yaml. The scheduler
 * reads the evaluation interval from here instead of a hard-coded number.
 * Secrets do not belong in this class. Those stay in the environment.
 *
 * Configuration properties for the EnerFlow automation scheduler.
 * Values are located in application.yaml under the "enerflow" prefix.
 *
 * @author saki
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "enerflow")
public class EnerflowProperties {

    private long evaluationIntervalMs;
}
