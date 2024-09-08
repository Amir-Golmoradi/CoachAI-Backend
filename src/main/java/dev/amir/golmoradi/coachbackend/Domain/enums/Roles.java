package dev.amir.golmoradi.coachbackend.Domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Roles {
    ADMIN(
            Set.of(
                    RolePermissions.ADMIN_READ,
                    RolePermissions.COACH_READ,

                    RolePermissions.ADMIN_CREATE,
                    RolePermissions.COACH_CREATE,

                    RolePermissions.ADMIN_UPDATE,
                    RolePermissions.COACH_UPDATE,

                    RolePermissions.ADMIN_DELETE,
                    RolePermissions.COACH_DELETE
            )
    ),
    COACH(
            Set.of(
                    RolePermissions.COACH_READ,
                    RolePermissions.COACH_CREATE,
                    RolePermissions.COACH_UPDATE,
                    RolePermissions.COACH_DELETE

            )
    ),
    ATHLETE(Collections.emptySet());


    private final Set<RolePermissions> permissions;
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
