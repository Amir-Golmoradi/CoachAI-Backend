package dev.amir.golmoradi.coachbackend.Domain.usecase.implementations.authImpl;

import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationRequest;
import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationResponse;
import dev.amir.golmoradi.coachbackend.Domain.service.AuthenticationService;
import dev.amir.golmoradi.coachbackend.Domain.usecase.interfaces.IAuthenticationUseCase;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUseCaseImpl implements IAuthenticationUseCase {

    private final AuthenticationService authService;

    public AuthenticationUseCaseImpl(AuthenticationService authService) {
        this.authService = authService;
    }

    @Override
    public AuthenticationResponse loginUser(AuthenticationRequest request) {
        return authService.login(new AuthenticationRequest(request.username(), request.password()));
    }
}
