package com.jobportal.dto;

import com.jobportal.entity.JobType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// basically this request is done from the recruiter end
// so you have to choose field from the recruiter point of view
// basically we are not using the notnull / nullable such thing becuase JobRequest is not database table
// whereas entity table was actually database table

public class JobRequest {
    private String title;
    private String company;
    private String description;
    private String location;
    private Double salary;
    private Integer experienceRequired;
    private String skills;
    private JobType jobType;
}
