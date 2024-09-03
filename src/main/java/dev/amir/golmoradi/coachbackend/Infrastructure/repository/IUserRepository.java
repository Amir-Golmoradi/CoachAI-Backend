package dev.amir.golmoradi.coachbackend.Infrastructure.repository;

import dev.amir.golmoradi.coachbackend.Domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {


    boolean existsUsersById(Long userId);

    User findUserByEmail(String email);

    boolean existsUsersByEmail(String email);
}
