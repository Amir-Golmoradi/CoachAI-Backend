package dev.amir.golmoradi.coachbackend.Core.requests;

import dev.amir.golmoradi.coachbackend.Domain.entity.Token;
import dev.amir.golmoradi.coachbackend.Domain.enums.Gender;
import dev.amir.golmoradi.coachbackend.Domain.enums.Roles;

import java.util.List;
import java.util.Set;

public record UserRegistrationRequest(
        Long id,
        Integer age,
        String username,
        String email,
        String password,
        Gender gender,
        Roles roles,
        List<Token> tokens
) {
}
