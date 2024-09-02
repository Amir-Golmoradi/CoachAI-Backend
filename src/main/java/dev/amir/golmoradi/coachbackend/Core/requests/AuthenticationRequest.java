package dev.amir.golmoradi.coachbackend.Core.requests;

public record AuthenticationRequest(
        String username,
        String password
) {
}
