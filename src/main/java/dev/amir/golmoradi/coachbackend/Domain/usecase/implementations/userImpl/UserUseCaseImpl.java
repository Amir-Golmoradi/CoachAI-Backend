package dev.amir.golmoradi.coachbackend.Domain.usecase.implementations.userImpl;

import dev.amir.golmoradi.coachbackend.Core.requests.UserRegistrationRequest;
import dev.amir.golmoradi.coachbackend.Domain.service.UserService;
import dev.amir.golmoradi.coachbackend.Domain.usecase.interfaces.IUserUseCase;
import dev.amir.golmoradi.coachbackend.Infrastructure.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUseCaseImpl implements IUserUseCase {

    private final UserService userService;


    public UserUseCaseImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createUserUseCase(UserRegistrationRequest request) {
        userService.createNewUser(request);
    }

    @Override
    public List<UserDTO> getAllUserUseCase() {
        return userService.getAllUser();
    }

    @Override
    public UserDTO getUserByIdUseCase(Long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public void deleteUserByIdSUseCase(Long userId) {
        userService.deleteUserById(userId);
    }

}
