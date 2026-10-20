/**
 * Domain objects: the nouns EnerFlow stores and reasons about.
 *
 * <p>These classes are JPA entities and small enums. They describe a snapshot,
 * a control action, a user, or the one row that remembers whether automation
 * is on. They are not DTOs for the browser and they are not the JSON a heat
 * pump sends. Adapters map those outside shapes into these objects.
 *
 * <p>Putting {@code @Entity} here is a deliberate shortcut for a portfolio
 * project: the same class is the domain concept and the database row. A
 * stricter hexagon would keep a pure domain model and map it at the
 * repository boundary. The dependency rule still holds — nothing in this
 * package imports a vendor client.
 */
package de.saki.enerflow.core.domain;
