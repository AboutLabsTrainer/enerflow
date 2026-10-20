/**
 * Stateless authentication. EnerFlow does not keep an HTTP session.
 *
 * <p>Login checks the password once and returns a signed JWT. Every later
 * request carries that token. {@code JwtAuthenticationFilter} runs before
 * Spring's username/password filter and, when the token is valid, places the
 * user in the {@code SecurityContext}. Authorization rules then see a role
 * ({@code ROLE_USER}, {@code ROLE_MANAGER}, {@code ROLE_ADMIN}) instead of
 * an anonymous request.
 *
 * <p>The signing secret never lives in source control. It arrives through
 * {@code enerflow.security.jwt-secret}, which {@code application.yaml} reads
 * from the environment.
 */
package de.saki.enerflow.config.security;
