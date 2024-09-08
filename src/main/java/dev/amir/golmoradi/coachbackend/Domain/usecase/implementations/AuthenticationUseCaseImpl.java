package dev.amir.golmoradi.coachbackend.Domain.usecase.implementations;

import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationRequest;
import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationResponse;
import dev.amir.golmoradi.coachbackend.Core.requests.UserRegistrationRequest;
import dev.amir.golmoradi.coachbackend.Domain.service.AuthenticationService;
import dev.amir.golmoradi.coachbackend.Domain.usecase.interfaces.IAuthenticationUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationUseCaseImpl implements IAuthenticationUseCase {

    private final AuthenticationService authenticationService;

    public AuthenticationUseCaseImpl(AuthenticationService authService) {
        this.authenticationService = authService;
    }


    @Override
    public AuthenticationResponse register(UserRegistrationRequest registrationRequest) {
        return authenticationService.register(registrationRequest);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
