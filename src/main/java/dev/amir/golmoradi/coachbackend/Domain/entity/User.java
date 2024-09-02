package dev.amir.golmoradi.coachbackend.Domain.entity;

import dev.amir.golmoradi.coachbackend.Domain.enums.Gender;
import dev.amir.golmoradi.coachbackend.Domain.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

//@Table(name = "users", uniqueConstraints =
//        {@UniqueConstraint(columnNames = "user_email", name = "unique_user_email")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
//    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
//    @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "BIGINT")
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String password;

    //    @Enumerated(EnumType.STRING)
    private Gender gender;


    //    @Enumerated(EnumType.STRING)
//    @Column(name = "roles", nullable = false)
//    @ElementCollection(fetch = FetchType.EAGER, targetClass = Roles.class)
//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Roles> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Roles.ATHLETE.name()));
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "id = " + id + ", " + "name = " + name + ", " + "email = " + email + ", " + "age = " + age + ", " + "password = " + password + ")";
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

