package dev.amir.golmoradi.coachbackend.Core.requests;

public record AuthenticationRequest(
        String email,
        String password
) {
}
