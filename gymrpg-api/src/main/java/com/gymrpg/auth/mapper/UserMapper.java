package com.gymrpg.auth.mapper;

import com.gymrpg.auth.dto.UserResponse;
import com.gymrpg.auth.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(user.id, user.name, user.email, user.createdAt);
    }
}