package com.jobportal.dto;
// in this class basically what we will send when we apply will take place

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ApplicationRequest {
      ///  what we will be seding ?
      //  resume url, cover letter
      private String resumeUrl;
      private String coverLetter;

}
