package dev.amir.golmoradi.coachbackend.Domain.usecase.interfaces;

import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationRequest;
import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationResponse;
import dev.amir.golmoradi.coachbackend.Core.requests.UserRegistrationRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IAuthenticationUseCase {

    AuthenticationResponse register(UserRegistrationRequest registrationRequest);


    AuthenticationResponse authenticate(AuthenticationRequest request);


    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}


