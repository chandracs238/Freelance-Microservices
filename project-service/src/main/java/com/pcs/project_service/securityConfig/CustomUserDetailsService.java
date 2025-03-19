package com.pcs.project_service.securityConfig;

import com.pcs.project_service.clients.UserServiceClient;
import com.pcs.project_service.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceClient userServiceFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse userResponse = userServiceFeignClient.getUserByUsername(username);

        if (userResponse == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        System.out.println("Loaded User: " + userResponse.getUsername() + " | Password: " + userResponse.getPassword());

        if (userResponse.getUsername() == null || userResponse.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (userResponse.getPassword() == null || userResponse.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userResponse.getRole()));
        return new User(
                userResponse.getUsername(),
                userResponse.getPassword(),
                authorities
        );

    }
}

