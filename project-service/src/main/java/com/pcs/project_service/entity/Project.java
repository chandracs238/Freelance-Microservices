package com.pcs.project_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private String status; // "OPEN", "IN_PROGRESS", "COMPLETED"

    private Long clientId;  // References Client (from User Service)
    private Long freelancerId; // Assigned Freelancer (from User Service)

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

