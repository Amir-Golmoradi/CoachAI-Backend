package dev.amir.golmoradi.coachbackend.Domain.service;

import dev.amir.golmoradi.coachbackend.Core.mapper.UserDTOMapper;
import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationRequest;
import dev.amir.golmoradi.coachbackend.Core.requests.AuthenticationResponse;
import dev.amir.golmoradi.coachbackend.Domain.entity.User;
import dev.amir.golmoradi.coachbackend.Infrastructure.dto.UserDTO;
import dev.amir.golmoradi.coachbackend.Infrastructure.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserDTOMapper dtoMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(
            UserDTOMapper dtoMapper,
            JwtTokenUtil jwtTokenUtil,
            AuthenticationManager authenticationManager
    ) {
        this.dtoMapper = dtoMapper;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }


    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        User principal = (User) authentication.getPrincipal();
        UserDTO userDTO = dtoMapper.apply(principal);
        String token = jwtTokenUtil.generateToken(userDTO.userName());
        return new AuthenticationResponse(token, userDTO);
    }

}
