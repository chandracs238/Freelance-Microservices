package com.pcs.user_service.controller;

import com.pcs.user_service.service.dto.UserResponse;
import com.pcs.user_service.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String username) {
        UserResponse user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/batch")
    public ResponseEntity<Map<Long, UserResponse>> getUsersByIds(@RequestParam List<Long> userIds) {
        Map<Long, UserResponse> users = userService.getUsersByIds(userIds);
        return ResponseEntity.ok(users);
    }
}

