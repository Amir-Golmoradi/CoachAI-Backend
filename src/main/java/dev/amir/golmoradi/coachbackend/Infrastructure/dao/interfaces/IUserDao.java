package dev.amir.golmoradi.coachbackend.Infrastructure.dao.interfaces;

import dev.amir.golmoradi.coachbackend.Domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserDao {

    List<User> selectAllUsers();

    Optional<User> selectUsersById(Long userId);

    void deleteUserById(Long userId);

    boolean existUserById(Long userId);

    boolean existUserByEmail(String email);

    Optional<User> selectUserByEmail(String email);

    void addUser(User user);
}

