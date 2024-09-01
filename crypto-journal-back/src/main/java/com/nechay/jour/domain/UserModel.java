package com.nechay.jour.domain;

import com.nechay.jour.config.entities.Credentials;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author anechaev
 * @since 05.05.2024
 */
@Table("users")
public class UserModel implements UserDetails, Credentials {

    @Id
    @Column("id")
    private long id;

    @Version
    @Column("version")
    private Long version;

    @Column("login")
    private String login;

    @Column("password")
    private String password;

    @Column("email")
    private String email;

    @Column("is_active")
    private Boolean isActive;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Transient
    private Set<UserRoleAssignmentModel> roleAssignments;

    public UserModel() {
        this.roleAssignments = new HashSet<>();
    }

    public UserModel(long id, long version, String login, String password, String email, Boolean isActive, Set<UserRoleAssignmentModel> roleAssignments) {
        this.id = id;
        this.version = version;
        this.login = login;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
        this.roleAssignments = roleAssignments;
    }

    @Nonnull
    public static UserModel from(@Nonnull UserModel model, @Nonnull String password) {
        return new UserModel(
            model.getId(),
            model.getVersion(),
            model.getLogin(),
            password,
            model.getEmail(),
            model.getActive(),
            model.getRoleAssignments()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleAssignments()
            .stream()
            .map(UserRoleAssignmentModel::getRole)
            .map(UserRoleModel::getRolePermissions)
            .flatMap(Set::stream)
            .map(UserRolePermissionModel::getUserPermission)
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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
        return getActive();
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Set<UserRoleAssignmentModel> getRoleAssignments() {
        return roleAssignments;
    }

    @Nonnull
    @Override
    public String username() {
        return login;
    }

    @Nonnull
    @Override
    public String password() {
        return password;
    }

    public static class Builder {
        private Long version = 0l;
        private String login;
        private String password;
        private String email;
        private Boolean isActive;
        private Set<UserRoleAssignmentModel> roleAssignments;

        public Builder setVersion(Long version) {
            this.version = version;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setActive(Boolean active) {
            isActive = active;
            return this;
        }

        public Builder setRoleAssignments(Set<UserRoleAssignmentModel> roleAssignments) {
            this.roleAssignments = roleAssignments;
            return this;
        }

        public UserModel build() {
            return new UserModel(0, version, login, password, email, isActive, roleAssignments);
        }
    }
}
