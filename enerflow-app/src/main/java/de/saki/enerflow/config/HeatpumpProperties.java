package de.saki.enerflow.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for the Novelan heat pump connection.
 * Values are located in application.yaml under the "heatpump" prefix.
 *
 * @author saki
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "heatpump")
public class HeatpumpProperties {

    // Getters and setters
    private String host;
    private int port;
    private String password;
    private int reconnectDelaySeconds;
    private long pollingIntervalMs;

    /** Safety range for hot water setpoint (the device supports 30–65 °C). */
    private double minSetpointCelsius = 30.0;
    private double maxSetpointCelsius = 65.0;

}
