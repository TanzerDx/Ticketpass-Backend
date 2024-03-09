package com.example.individual_assignment_hristo_ganchev.security.token;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
