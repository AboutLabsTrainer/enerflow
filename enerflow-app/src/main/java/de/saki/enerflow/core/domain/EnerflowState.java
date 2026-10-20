package de.saki.enerflow.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The memory of the automation, one row with id 1. enabled is the user
 * switch. boostActive means the setpoint is currently raised.
 * lastKnownSetpoint is the value to restore. Persisting it means a
 * restart can still RESET instead of leaving the tank at 55 degrees.
 *
 * Singleton entity representing the runtime state of the EnerFlow
 * automation logic. Exactly one row exists in the database at all times (id = 1).
 *
 * @author saki
 */
@Getter
@Setter
@Entity
@Table(name = "enerflow_state")
public class EnerflowState {

    @Id
    private Integer id;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "boost_active", nullable = false)
    private boolean boostActive;

    @Column(name = "last_known_setpoint")
    private Double lastKnownSetpoint;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;
}
