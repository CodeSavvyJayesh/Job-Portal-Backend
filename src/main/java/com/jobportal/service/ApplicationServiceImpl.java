package com.jobportal.service;

import com.jobportal.dto.ApplicationRequest;
import com.jobportal.dto.ApplicationResponse;
import com.jobportal.entity.ApplicationStatus;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Override
    public ApplicationResponse applyForJob(ApplicationRequest request)
    {
        return null;
    }

    @Override
    public List<ApplicationResponse> getMyApplications(){
        return null;
    }

    @Override
    public List<ApplicationResponse> getApplicationsForJob(Long jobId) {
        return List.of();
    }

    @Override
    public ApplicationResponse updateApplicationStatus(Long applicationId,
                                                       ApplicationStatus status)
    {
         return null;
    }



}
