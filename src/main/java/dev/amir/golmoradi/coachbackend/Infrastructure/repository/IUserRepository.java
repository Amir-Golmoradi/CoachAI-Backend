package dev.amir.golmoradi.coachbackend.Infrastructure.repository;

import dev.amir.golmoradi.coachbackend.Domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {


    boolean existsUsersById(Long userId);

    Optional<User> findUserByEmail(String email);

    boolean existsUsersByEmail(String email);
}
