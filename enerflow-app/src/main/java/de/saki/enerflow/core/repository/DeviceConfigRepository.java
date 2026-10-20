package de.saki.enerflow.core.repository;

import de.saki.enerflow.core.domain.DeviceConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Persistence port for the key-value settings. The id is the key string,
 * so getReference is a lookup by name, not by a numeric surrogate.
 *
 * Repository for accessing runtime-configurable EnerFlow parameters.
 *
 * @author saki
 */
public interface DeviceConfigRepository extends JpaRepository<DeviceConfig, String> {
}
