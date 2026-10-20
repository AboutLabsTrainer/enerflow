package de.saki.enerflow.adapter.web.dto;

import de.saki.enerflow.core.domain.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Inbound JSON for user creation. Validation annotations reject a short
 * password before the service runs. This record is allowed to mention
 * UserRole. The entity is not allowed to mention this record.
 *
 * Request payload for creating a new application user (ADMIN only).
 *
 * @author saki
 */
public record CreateUserRequest(

        @NotBlank(message = "Username must not be blank")
        @Size(min = 3, max = 100, message = "Username must be between 3 and 100 characters")
        String username,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @NotNull(message = "Role must be specified")
        UserRole role
) {
}