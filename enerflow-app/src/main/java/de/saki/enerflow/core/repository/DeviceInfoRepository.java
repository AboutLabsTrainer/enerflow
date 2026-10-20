package de.saki.enerflow.core.repository;

import de.saki.enerflow.core.domain.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Persistence port for hardware identity rows. The id is the device type.
 * There is no custom query because the service loads a row by that id.
 */
public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, String> {
}