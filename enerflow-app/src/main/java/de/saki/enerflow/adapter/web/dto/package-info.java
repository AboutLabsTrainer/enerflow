/**
 * Driving adapter for HTTP. Controllers are how a browser starts a use case.
 * DTOs are the JSON shape of that conversation.
 *
 * <p>A controller may call a service or a repository. It must not call a
 * heat pump or the battery. Requests and responses use records in this
 * package, not JPA entities, so a column rename does not silently change
 * the API — except where a record is mapped explicitly in a {@code fromEntity}
 * method, which is the translation you can see.
 */
package de.saki.enerflow.adapter.web.dto;
