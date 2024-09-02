package dev.amir.golmoradi.coachbackend.Core.mapper;

import dev.amir.golmoradi.coachbackend.Domain.entity.User;
import dev.amir.golmoradi.coachbackend.Domain.enums.Gender;
import dev.amir.golmoradi.coachbackend.Domain.enums.Roles;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setName(rs.getString("user_name"));
        user.setEmail(rs.getString("user_email"));
        user.setAge(rs.getInt("user_age"));
        user.setPassword(rs.getString("password")); // Make sure the column name is correct
        user.setGender(Gender.valueOf(rs.getString("gender").toUpperCase())); // Assuming gender is stored as a String

        // Placeholder for roles - you will need to fetch the roles from the user_roles table
        Set<Roles> roles = fetchRolesForUser(user.getId());
        user.setRoles(roles);

        return user;
    }

    private Set<Roles> fetchRolesForUser(Long userId) {
        // This method should query the user_roles table to get the roles for the given userId
        // For example:
        // String sql = "SELECT role FROM user_roles WHERE user_id = ?";
        // Use JdbcTemplate to execute the query and map the results to a Set<Roles>
        // This is a placeholder; you would need to implement this with JdbcTemplate
        // Assuming you have a JdbcTemplate instance available
        // jdbcTemplate.query(sql, new Object[]{userId}, (rs) -> {
        //     roles.add(Roles.valueOf(rs.getString("role")));
        // });
        return new HashSet<>();
    }
}
