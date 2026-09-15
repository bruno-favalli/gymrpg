package com.gymrpg.auth.service;

import com.gymrpg.auth.dto.LoginRequest;
import com.gymrpg.auth.dto.LoginResponse;
import com.gymrpg.auth.dto.RegisterRequest;
import com.gymrpg.auth.dto.UserResponse;
import com.gymrpg.auth.entity.User;
import com.gymrpg.auth.mapper.UserMapper;
import com.gymrpg.auth.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;

import java.time.Instant;

@ApplicationScoped
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, UserMapper userMapper, TokenService tokenService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }

    @Transactional
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new WebApplicationException("E-mail já cadastrado", Response.Status.CONFLICT);
        }

        User user = new User();
        user.name = request.name();
        user.email = request.email();
        user.passwordHash = BCrypt.hashpw(request.password(), BCrypt.gensalt());
        user.createdAt = Instant.now();

        userRepository.persist(user);

        return userMapper.toResponse(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new WebApplicationException("E-mail ou senha inválidos", Response.Status.UNAUTHORIZED));

        boolean senhaCorreta = BCrypt.checkpw(request.password(), user.passwordHash);

        if (!senhaCorreta) {
            throw new WebApplicationException("E-mail ou senha inválidos", Response.Status.UNAUTHORIZED);
        }

        String token = tokenService.generateToken(user);

        return new LoginResponse(token, userMapper.toResponse(user));
    }
}