package com.gymrpg.auth.dto;

public record LoginResponse(
    String token,
    UserResponse user
) {}