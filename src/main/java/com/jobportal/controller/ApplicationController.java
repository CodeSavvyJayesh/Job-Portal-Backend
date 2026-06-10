package com.jobportal.controller;

import com.jobportal.dto.ApplicationRequest;
import com.jobportal.dto.ApplicationResponse;
import com.jobportal.entity.ApplicationStatus;
import com.jobportal.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// in this we have to write get/post methods for application control
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicationController {
    // we have to basically need object of service layer
    private final ApplicationService applicationService;

    // user applied for the job
    @PostMapping("/user/applications")
    public ApplicationResponse applyForJob(@RequestBody ApplicationRequest request)
    {
         return applicationService.applyForJob(request);
    }
    // user views all his applictaion
    @GetMapping("/user/applications")
    public List<ApplicationResponse> getMyApplications(){
        return applicationService.getMyApplications();
    }
    // recruiter views all the applications that he recieved for particular job
    @GetMapping("/recruiter/jobs/{jobId}/applictaions")
    public List<ApplicationResponse> getApplicationsForJob(@PathVariable Long jobId)
    {
         return applicationService.getApplicationsForJob(jobId);
    }
    // recruiter updates the application
    @PutMapping("/recruiter/applictaions/{applicationId}/status")
    public ApplicationResponse updatedApplicationStatus(@PathVariable Long applicationId,
                                                        ApplicationStatus status)
    {
        return applicationService.updateApplicationStatus(applicationId,status);
    }

}
