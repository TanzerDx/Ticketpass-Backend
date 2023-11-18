package com.example.individual_assignment_hristo_ganchev.security.token;


import java.util.List;

public interface AccessToken {
    String getSubject();

    Long getUserId();

    String getRoles();

    boolean hasRole(String roleName);
}
