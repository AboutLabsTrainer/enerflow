package de.saki.enerflow.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Host and API token for the sonnen adapter. The token is a secret and
 * comes from the environment via application.yaml. The core does not
 * know this host exists.
 *
 * Configuration properties for the sonnenBatterie local REST API.
 * Values are located in application.yaml under the "sonnen" prefix.
 *
 * @author saki
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "sonnen")
public class SonnenBatteryProperties {

    private String host;
    private String apiToken;
}
