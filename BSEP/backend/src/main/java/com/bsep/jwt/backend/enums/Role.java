package com.bsep.jwt.backend.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CLIENT,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
