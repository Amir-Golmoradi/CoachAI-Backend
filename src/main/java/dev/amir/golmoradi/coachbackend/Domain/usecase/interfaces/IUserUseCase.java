package dev.amir.golmoradi.coachbackend.Domain.usecase.interfaces;

import dev.amir.golmoradi.coachbackend.Core.requests.UserRegistrationRequest;
import dev.amir.golmoradi.coachbackend.Infrastructure.dto.UserDTO;

import java.util.List;

public interface IUserUseCase {

    void createUserUseCase(UserRegistrationRequest request);

    List<UserDTO> getAllUserUseCase();

    UserDTO getUserByIdUseCase(Long userId);

    void deleteUserByIdSUseCase(Long userId);
}
