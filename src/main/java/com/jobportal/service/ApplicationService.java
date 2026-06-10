package com.jobportal.service;

import com.jobportal.dto.ApplicationRequest;
import com.jobportal.dto.ApplicationResponse;
import com.jobportal.entity.ApplicationStatus;
import org.springframework.stereotype.Service;

import java.util.List;

// this class will consist the business logic
// we have to use ApplicationRepo in this class
public interface ApplicationService{
     // user clicks apply now ( apply button)
    // it will create new application
      ApplicationResponse applyForJob(ApplicationRequest request);

      // this will be for get my application

      // recruiter view application

      List<ApplicationResponse> getApplicationsForJob(Long jobId);

      // updated status
      ApplicationResponse updateApplicationStatus(
              Long applicationId, ApplicationStatus status
      );

}
