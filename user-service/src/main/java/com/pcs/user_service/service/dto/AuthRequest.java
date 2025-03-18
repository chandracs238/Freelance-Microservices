package com.pcs.user_service.service.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
