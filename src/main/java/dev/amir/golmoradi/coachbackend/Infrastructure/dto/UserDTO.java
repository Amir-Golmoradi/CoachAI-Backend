package dev.amir.golmoradi.coachbackend.Infrastructure.dto;


public record UserDTO(
        Long id,
        String userName,
        String email,
        Integer age
) {
}
