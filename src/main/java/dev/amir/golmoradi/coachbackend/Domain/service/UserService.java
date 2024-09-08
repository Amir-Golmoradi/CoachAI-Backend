package dev.amir.golmoradi.coachbackend.Domain.service;

import dev.amir.golmoradi.coachbackend.Core.exceptions.DuplicationException;
import dev.amir.golmoradi.coachbackend.Core.exceptions.ResourceNotFound;
import dev.amir.golmoradi.coachbackend.Core.mapper.UserDTOMapper;
import dev.amir.golmoradi.coachbackend.Core.requests.UserRegistrationRequest;
import dev.amir.golmoradi.coachbackend.Domain.entity.User;
import dev.amir.golmoradi.coachbackend.Domain.enums.Roles;
import dev.amir.golmoradi.coachbackend.Infrastructure.dao.interfaces.IUserDao;
import dev.amir.golmoradi.coachbackend.Infrastructure.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final IUserDao userDao;
    private final UserDTOMapper mapper;


    public UserService(IUserDao userDao, UserDTOMapper mapper) {
        this.userDao = userDao;
        this.mapper = mapper;
    }

    public List<UserDTO> getAllUser() {
        return userDao.selectAllUsers()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long userId) {
        return userDao.selectUsersById(userId)
                .map(mapper)
                .orElseThrow(
                        () -> new ResourceNotFound("customer with following id [%s] not found".formatted(userId))
                );
    }

    public void deleteUserById(Long userId) {
        if (!userDao.existUserById(userId)) {
            throw new ResourceNotFound("customer with following id [%s] not found".formatted(userId));
        }
        userDao.deleteUserById(userId);
    }
    /* CREATE NEW USER */


    public void createNewUser(UserRegistrationRequest registrationRequest) {
        Set<Roles> userRoleSet = new HashSet<>();
        userRoleSet.add(Roles.ATHLETE);
        String email = registrationRequest.email();
        if (userDao.existUserByEmail(email)) {
            throw new DuplicationException(
                    "email is taken [%s]".formatted(email)
            );
        }

        User newUser = new User(
                registrationRequest.id(),
                registrationRequest.username(),
                registrationRequest.email(),
                registrationRequest.age(),
                registrationRequest.password(),
                registrationRequest.gender(),
                registrationRequest.tokens(),
                registrationRequest.roles()
        );
        userDao.addUser(newUser);
    }
}
