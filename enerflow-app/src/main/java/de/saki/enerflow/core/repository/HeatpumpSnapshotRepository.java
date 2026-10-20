package de.saki.enerflow.core.repository;

import de.saki.enerflow.core.domain.HeatpumpSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Persistence port for heat-pump snapshots. Same derived-query style as
 * the battery repository: latest row, a time window, and a delete for
 * retention. The dashboard and the retention job are the callers.
 *
 * Repository for storing and retrieving heat pump snapshots.
 * Spring Data JPA generates all queries automatically.
 */
@Repository
public interface HeatpumpSnapshotRepository extends JpaRepository<HeatpumpSnapshot, Long> {

    long deleteByTimestampBefore(LocalDateTime threshold);

    List<HeatpumpSnapshot> findByTimestampBetween(
            LocalDateTime from,
            LocalDateTime to);

    Optional<HeatpumpSnapshot> findTopByOrderByTimestampDesc();
}
