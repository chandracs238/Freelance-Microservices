package com.pcs.project_service.controller;

import com.pcs.project_service.clients.UserServiceClient;
import com.pcs.project_service.dto.ProjectResponse;
import com.pcs.project_service.dto.UserResponse;
import com.pcs.project_service.entity.Project;
import com.pcs.project_service.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserServiceClient userServiceClient;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id) {
        ProjectResponse response = projectService.getProjectWithUsers(id);
        return ResponseEntity.ok(response);
    }


}

