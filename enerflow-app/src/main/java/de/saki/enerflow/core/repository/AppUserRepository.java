package de.saki.enerflow.core.repository;

import de.saki.enerflow.core.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Persistence port for accounts. findByUsername is a query derived from
 * the method name. Spring Data implements it. Services depend on this
 * interface, so tests can replace it without PostgreSQL.
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}