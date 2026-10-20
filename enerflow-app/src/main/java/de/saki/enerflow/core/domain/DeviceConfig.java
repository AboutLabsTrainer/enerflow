package de.saki.enerflow.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * One key-value row of operator settings: thresholds, setpoints, tank
 * volume, retention. Strings in the table, numbers in DeviceConfigService.
 * Changing a row takes effect on the next evaluation cycle. No restart.
 *
 * Persistent key-value store for runtime-configurable EnerFlow parameters.
 * Values can be updated in the database without restarting the application.
 *
 * @author saki
 */
@Getter
@Setter
@Entity
@Table(name = "device_config")
public class DeviceConfig {

    @Id
    @Column( name=  "config_key", nullable = false, length = 100)
    private String configKey;

    @Column(name = "config_value", nullable = false, length =255)
    private String configValue;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;
}
