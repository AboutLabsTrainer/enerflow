package de.saki.enerflow.core.domain;

/**
 * The three roles, stored as enum names. The ROLE_ prefix matches what
 * Spring Security's hasRole checks expect. A homeowner, a plant manager,
 * and an administrator are different people. The enum is the whole model.
 *
 * Role of an EnerFlow user, used for role-based access control (AP3.3).
 *
 * @author saki
 */
public enum UserRole {
    ROLE_USER,
    ROLE_MANAGER,
    ROLE_ADMIN
}