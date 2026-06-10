package com.jobportal.dto;

import com.jobportal.entity.JobType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {
    // basically this class will consist of what information should frontend display ?
    // frontend should see the :
    // when job was created
    // till when its valid
    private Long id;
    private String title;
    private String company;
    private String description;
    private String location;
    private Double salary;
    private Integer experienceRequired;
    private String skills;
    private JobType jobType;
    private LocalDateTime createdAt;
    private String recuiterName;
    private String recruiterEmail;
}
