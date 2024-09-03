package dev.amir.golmoradi.coachbackend.Infrastructure.dao.implementations;

import dev.amir.golmoradi.coachbackend.Core.mapper.UserRowMapper;
import dev.amir.golmoradi.coachbackend.Domain.entity.User;
import dev.amir.golmoradi.coachbackend.Infrastructure.dao.interfaces.IUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDataAccessService implements IUserDao {

    private final UserRowMapper rowMapper;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> selectAllUsers() {
        String query = "SELECT * FROM users";
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public Optional<User> selectUsersById(Long userId) {
        String query = "SELECT * FROM users WHERE user_id=:userId";
        Map<String, Object> params = Collections.singletonMap("userId", userId);
        return jdbcTemplate.query(query, rowMapper, params).stream().findFirst();
    }

    @Override
    public void deleteUserById(Long userId) {
        String query = "DELETE FROM users WHERE user_id = :userId";
        Map<String, Object> params = Collections.singletonMap("userId", userId);
        jdbcTemplate.update(query, params);

    }

    @Override
    public boolean existUserById(Long userId) {
        String query = "SELECT count(user_id)FROM users WHERE user_id = :userId";
        Map<String, Object> params = Collections.singletonMap("userId", userId);
        Long count = jdbcTemplate.queryForObject(query, Long.class, params);
        return count != null && count > 0;
    }

    @Override
    public boolean existUserByEmail(String email) {
        String query = "SELECT count(user_email)FROM users WHERE user_email =:email";
        Map<String, Object> params = Collections.singletonMap("email", email);
        Long count = jdbcTemplate.queryForObject(query, Long.class, params);
        return count != null && count > 0;
    }

    @Override
    public Optional<User> selectUserByEmail(String email) {
        String query = "SELECT user_id,user_name,user_email,user_age FROM users WHERE user_email =:email";
        Map<String, Object> params = Collections.singletonMap("email", email);
        return jdbcTemplate.query(query, rowMapper, params).stream().findFirst();
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users(user_id,user_name,user_email,user_age,password,gender) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(query, user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAge());
    }
}
