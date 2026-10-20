package de.saki.enerflow.core.repository;

import de.saki.enerflow.core.domain.ControlLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Persistence port for the audit log. The method name is the query:
 * rows between two times, newest first. The service that writes a RAISE
 * does not know the table name.
 */
@Repository
public interface ControlLogRepository extends JpaRepository<ControlLog, Long> {

    List<ControlLog> findByTimestampBetweenOrderByTimestampDesc(LocalDateTime from, LocalDateTime to);
}