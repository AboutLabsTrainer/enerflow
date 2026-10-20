package de.saki.enerflow.core.service;

/**
 * Domain failure for user creation, not an HTTP status. The service throws
 * it. The controller translates it into a 409. That split keeps the
 * service usable from a test that has no web layer.
 *
 * Thrown when attempting to create a user with a username that is already taken.
 *
 * @author saki
 */
public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException(String username) {
        super("Username already exists: " + username);
    }
}