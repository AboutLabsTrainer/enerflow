package de.saki.enerflow.core.repository;

import de.saki.enerflow.core.domain.ElectricityPriceChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence port for the price history. Append-only at the service
 * level: callers save new rows and do not edit old ones. The interface
 * cannot enforce that. The service must.
 *
 * Repository for the electricity price change log (append-only).
 *
 * @author saki
 */
@Repository
public interface ElectricityPriceChangeLogRepository
        extends JpaRepository<ElectricityPriceChangeLog, Long> {
}