package de.saki.enerflow.adapter.web.dto;

import de.saki.enerflow.core.domain.AppUser;
import de.saki.enerflow.core.domain.UserRole;

import java.time.LocalDateTime;

/**
 * API shape, not the database row. The password hash and the failed-login
 * counter never leave the server. fromEntity is the translation. Clients
 * depend on this record. They must not depend on the AppUser entity.
 *
 * Response payload representing an application user, safe for API exposure.
 * Never includes the password hash or failed-login counter.
 *
 * @author saki
 */
public record AppUserResponse(
        Long id,
        String username,
        UserRole role,
        boolean enabled,
        boolean locked,
        LocalDateTime createdAt,
        LocalDateTime lastLogin
) {

    public static AppUserResponse fromEntity(AppUser user) {
        boolean isLocked = user.getLockedUntil() != null
                && user.getLockedUntil().isAfter(LocalDateTime.now());

        return new AppUserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.isEnabled(),
                isLocked,
                user.getCreatedAt(),
                user.getLastLogin()
        );
    }
}