package com.example.individual_assignment_hristo_ganchev.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
