package dev.amir.golmoradi.coachbackend.Core.requests;

import dev.amir.golmoradi.coachbackend.Infrastructure.dto.UserDTO;

public record AuthenticationResponse(
        String token,
        UserDTO userDTO
) {
}
