package com.pcs.user_service.service;

import com.pcs.user_service.entity.RoleType;
import com.pcs.user_service.entity.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByUsername(String username);
}

