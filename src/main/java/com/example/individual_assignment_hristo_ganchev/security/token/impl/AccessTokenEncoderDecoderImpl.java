package com.example.individual_assignment_hristo_ganchev.security.token.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessTokenDecoder;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessTokenEncoder;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;



import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessTokenEncoderDecoderImpl implements AccessTokenEncoder, AccessTokenDecoder {
    private final Key key;
    private static final Logger logger = LoggerFactory.getLogger(AccessTokenEncoderDecoderImpl.class);

    Dotenv dotenv = Dotenv.configure()
            .directory("D:\\Uni\\Semester 3\\Individual Project")
            .filename("jwt-secret-key.env")
            .load();

    String obtainedSecretKey = dotenv.get("jwt.secret");

    public AccessTokenEncoderDecoderImpl() {
        byte[] keyBytes = Decoders.BASE64.decode(obtainedSecretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String encode(AccessToken accessToken) {
        Map<String, Object> claimsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(accessToken.getRoles())) {
            claimsMap.put("roles", accessToken.getRoles());
        }
        if (accessToken.getUserId() != null) {
            claimsMap.put("id", accessToken.getUserId());
        }

        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }

    @Override
    public AccessToken decode(String accessTokenEncoded) {
        try {
            Jwt<?, Claims> jwt = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(accessTokenEncoded);
            Claims claims = jwt.getBody();

            List<String> roles = claims.get("roles", List.class);
            Long userId = claims.get("id", Long.class);

            return new AccessTokenImpl(claims.getSubject(), userId, roles);
        } catch (JwtException e) {
            logger.error("Error decoding access token: {}", e.getMessage(), e);
            return null;
        }
    }
}
