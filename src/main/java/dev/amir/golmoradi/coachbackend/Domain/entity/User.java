package dev.amir.golmoradi.coachbackend.Domain.entity;

import dev.amir.golmoradi.coachbackend.Domain.enums.Gender;
import dev.amir.golmoradi.coachbackend.Domain.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_user_email",
                        columnNames = "user_email")})
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "user_name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "user_email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "user_age", nullable = false, columnDefinition = "Integer")
    private Integer age;

    @Column(name = "user_password", nullable = false, columnDefinition = "Text")
    private String password;

    @Column(name = "user_gender", nullable = false)
    @Enumerated(STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Enumerated(STRING)
    @Column(name = "user_role", nullable = false)
    private Roles roles;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", age=" + age + ", password='" + password + '\'' + ", gender=" + gender + ", roles=" + roles + '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.getAuthorities();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(age, user.age) && Objects.equals(password, user.password) && gender == user.gender && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, password, gender, roles);
    }
}

