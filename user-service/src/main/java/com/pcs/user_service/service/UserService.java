package com.pcs.user_service.service;

import com.pcs.user_service.entity.RoleType;
import com.pcs.user_service.entity.User;
import com.pcs.user_service.service.dto.UserResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByUsername(String username);
    UserResponse getUserById(Long id);
    UserResponse getUserByUsername(String username);
    Map<Long, UserResponse> getUsersByIds(List<Long> userIds);
}

