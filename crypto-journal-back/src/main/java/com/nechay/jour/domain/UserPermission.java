package com.nechay.jour.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 05.05.2024
 */
public enum UserPermission implements GrantedAuthority {
    ;

    private final String permission;

    UserPermission(@Nonnull String permission) {
        this.permission = permission;
    }

    @Override
    @Nonnull
    public String getAuthority() {
        return permission;
    }
}
