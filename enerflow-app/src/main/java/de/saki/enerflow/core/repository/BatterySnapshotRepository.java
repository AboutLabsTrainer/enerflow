package de.saki.enerflow.core.repository;

import de.saki.enerflow.core.domain.BatterySnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Persistence port for battery snapshots. findTopByOrderByTimestampDesc
 * is the latest row, which is all the dashboard needs.
 * deleteByTimestampBefore is the retention job. No SQL string is written
 * in this project for either question.
 */
@Repository
public interface BatterySnapshotRepository extends JpaRepository<BatterySnapshot, Long> {

    long deleteByTimestampBefore(LocalDateTime threshold);

    Optional<BatterySnapshot> findTopByOrderByTimestampDesc();
}
