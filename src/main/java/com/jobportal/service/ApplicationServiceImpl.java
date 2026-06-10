package com.jobportal.service;

import com.jobportal.dto.ApplicationRequest;
import com.jobportal.dto.ApplicationResponse;
import com.jobportal.entity.Application;
import com.jobportal.entity.ApplicationStatus;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        // baiscally user have to do all action
        User user = userRepository.findByEmail("jayeshdhamal03@gmail.com").orElseThrow(() ->
                new RuntimeException("User not found "));

        Job job = jobRepository.findById(request.getJobId()).orElseThrow(() -> new RuntimeException(
                "Job not found"
        ));

        Application application = Application.builder()
                        .user(user).job(job).resumeUrl(request.getResumeUrl()).
                status(ApplicationStatus.PENDING).appliedAt(LocalDateTime.now()).build();


        Application savedApplication = applicationRepository.save(application);

        return ApplicationResponse.builder().applicationId(savedApplication.getId()).
                message("Application submitted successfully").jobTitle(job.getTitle()).company(job.getCompany()).
                status(savedApplication.getStatus().name()).appliedAt(savedApplication.getAppliedAt()).build();

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
