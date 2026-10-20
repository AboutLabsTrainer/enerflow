/**
 * Persistence ports. Each interface is the only way a service reads or
 * writes one table.
 *
 * <p>Spring Data JPA implements these interfaces at startup. You will not
 * find a class that runs SQL by hand. Declaring {@code findTopByOrderByTimestampDesc}
 * is enough: the method name is the query. Services depend on the interface,
 * so tests can substitute a fake repository and the service does not know
 * PostgreSQL exists.
 *
 * <p>The tables themselves are created by Flyway, not by these interfaces.
 * {@code ddl-auto: validate} means Hibernate checks that the entities match
 * the migrations and refuses to invent columns.
 */
package de.saki.enerflow.core.repository;
