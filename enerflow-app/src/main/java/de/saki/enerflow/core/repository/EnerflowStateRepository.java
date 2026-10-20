package de.saki.enerflow.core.repository;

import de.saki.enerflow.core.domain.EnerflowState;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Persistence port for the singleton automation row. EnergyManagerService
 * loads id 1 on every cycle. If that row is missing, startup migrations
 * were not applied. The service treats that as a broken installation.
 *
 * Repository for the singleton EnerFlow automation state.
 *
 * @author saki
 */
public interface EnerflowStateRepository extends JpaRepository<EnerflowState, Integer> {
}
