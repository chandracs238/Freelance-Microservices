package com.pcs.project_service.clients;

import com.pcs.project_service.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "user-service", url = "http://localhost:8081/api/auth/users")
public interface UserServiceClient {
    @GetMapping("/{username}")
    UserResponse getUserByUsername(@PathVariable("username") String username);

    @GetMapping("/batch")
    Map<Long, UserResponse> getUsersByIds(@RequestParam List<Long> userIds);
}

