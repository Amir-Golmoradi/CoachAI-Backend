package dev.amir.golmoradi.coachbackend.Domain.usecase.interfaces;

import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationRequest;
import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationResponse;

public interface IAuthenticationUseCase {

    AuthenticationResponse loginUser(AuthenticationRequest request);


}
