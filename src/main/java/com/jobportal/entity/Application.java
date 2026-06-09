package com.jobportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// this class will be for making table of application
@Entity
@Table(name = "application")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     // this id will be application id;
    // there are many applications that belongs to one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // many applications may belong to one single job
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Column(nullable = false, length = 500)
    private String coverLetter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime appliedAt;
}
