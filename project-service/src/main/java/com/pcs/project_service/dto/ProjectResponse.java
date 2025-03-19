package com.pcs.project_service.dto;

import com.pcs.project_service.entity.Project;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String status;

    private UserResponse client;
    private UserResponse freelancer;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

