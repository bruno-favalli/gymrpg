package com.gymrpg.auth.service;

import com.gymrpg.auth.entity.User;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Duration;
import java.util.Set;

@ApplicationScoped
public class TokenService {

    @ConfigProperty(name = "gymrpg.jwt.duration-seconds")
    long durationSeconds;

    public String generateToken(User user) {
        return Jwt.issuer("gymrpg-api")
                .subject(String.valueOf(user.id))
                .groups(Set.of("user"))
                .expiresIn(Duration.ofSeconds(durationSeconds))
                .sign();
    }
}