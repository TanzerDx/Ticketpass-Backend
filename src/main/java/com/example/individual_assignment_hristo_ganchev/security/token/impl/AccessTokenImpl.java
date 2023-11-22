package com.example.individual_assignment_hristo_ganchev.security.token.impl;

import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;


@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken {
    private final String subject;
    private final Long userId;
    private final List<String> roles;

    public AccessTokenImpl(String subject, Long userId, List<String> roles) {
        this.subject = subject;
        this.userId = userId;
        this.roles = roles;
    }

    @Override
    public boolean hasRole(String roleName) {
        return this.roles.contains(roleName);
    }
}


