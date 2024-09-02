package dev.amir.golmoradi.coachbackend.Presentation.controllers;

import dev.amir.golmoradi.coachbackend.Core.requests.UserRegistrationRequest;
import dev.amir.golmoradi.coachbackend.Domain.entity.User;
import dev.amir.golmoradi.coachbackend.Domain.usecase.interfaces.IUserUseCase;
import dev.amir.golmoradi.coachbackend.Infrastructure.dto.UserDTO;
import dev.amir.golmoradi.coachbackend.Infrastructure.security.jwt.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${spring.application.user-route}")
public class UserController {
    public UserController(IUserUseCase userUseCase, JwtUtility jwtUtility) {
        this.userUseCase = userUseCase;
        this.jwtUtility = jwtUtility;
    }

    private final IUserUseCase userUseCase;
    private final JwtUtility jwtUtility;

    @GetMapping()
    public List<UserDTO> showAllUsers() {
        return userUseCase.getAllUserUseCase();
    }

    @GetMapping("{userId}")
    public UserDTO showUserById(@PathVariable("userId") Long userId) {
        return userUseCase.getUserByIdUseCase(userId);
    }

    @DeleteMapping("{userId}")
    public void deleteUserById(@PathVariable("userId") Long userId) {
        userUseCase.deleteUserByIdSUseCase(userId);
    }

    @PostMapping()
    public ResponseEntity<User> createNewUser(@RequestBody UserRegistrationRequest request) {
        userUseCase.createUserUseCase(request);
        String jwtToken = jwtUtility.generateToken(request.email());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }
}
