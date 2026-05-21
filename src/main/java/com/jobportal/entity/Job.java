package com.jobportal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 150)
    // this refers to title of job
    private String title;
    @Column(nullable = false, length = 120)
    // company name
    private String company;
    @Column(nullable = false, length = 3000)
    private String description;
    @Column(nullable = false, length = 100)
    private String location;
    private Double salary;
    @Column(nullable = false)
    private Integer experienceRequired;
    @Column(nullable = false, length = 500)
    private String skills;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType jobType;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // one recruiter -> many jobs

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private User recruiter;


}
