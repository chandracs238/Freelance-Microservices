package com.pcs.project_service.service;

import com.pcs.project_service.clients.UserServiceClient;
import com.pcs.project_service.dto.ProjectResponse;
import com.pcs.project_service.dto.UserResponse;
import com.pcs.project_service.entity.Project;
import com.pcs.project_service.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserServiceClient userServiceClient;

    public ProjectResponse getProjectWithUsers(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Fetch all users in one call (optimize Feign)
        List<Long> userIds = Stream.of(project.getClientId(), project.getFreelancerId())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Map<Long, UserResponse> users = userServiceClient.getUsersByIds(userIds);

        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .category(project.getCategory())
                .status(project.getStatus())
                .client(users.get(project.getClientId()))
                .freelancer(users.get(project.getFreelancerId()))
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .build();
    }
}

