package com.ada.web.planner.infra.security;

import com.ada.web.planner.core.model.User;
import com.ada.web.planner.dto.user.LoginResponse;
import com.ada.web.planner.infra.security.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final SecurityConfig securityConfig;

    public JwtService(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }

    public String generateToken(User authentication){
        Instant now = Instant.now();
        long expiresIn = 3600L;

        var claims = JwtClaimsSet.builder()
                .issuer("api_planner")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .subject(authentication.getId().toString())
                .build();

        return securityConfig.jwtEncoder().encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
