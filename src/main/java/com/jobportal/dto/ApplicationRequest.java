package com.jobportal.dto;
// in this class basically what we will send when we apply will take place

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ApplicationRequest {
      ///  what we will be seding ?
      private Long jobId;
      private String resumeUrl;
      private String coverLetter;

}
