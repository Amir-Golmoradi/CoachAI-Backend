package dev.amir.golmoradi.coachbackend.Presentation.controllers;

import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationRequest;
import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationResponse;
import dev.amir.golmoradi.coachbackend.Domain.usecase.interfaces.IAuthenticationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/${spring.application.auth-route}")
public class AuthenticationController {

    private final IAuthenticationUseCase authenticationUseCase;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationUseCase.loginUser(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.token())
                .body(response);
    }

}
