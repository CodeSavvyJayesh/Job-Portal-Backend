package com.jobportal.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {
    // in this response class we should get the response like what will happen after we apply
    // whether it should be rejected or accepted ?

    private Long applicationId;  // after doing application request this will geenerate
    private String message;
    private String jobTitle;
    private String company;
    private String status;     // current status of the application // intially it will be pending
    private LocalDateTime appliedAt;
}
