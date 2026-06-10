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
    public ApplicationResponse applyForJob(ApplicationRequest request) {

        User user = userRepository.findByEmail("jayeshdhamal03@gmail.com")
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (applicationRepository.existsByUserAndJob(user, job)) {
            throw new RuntimeException("You have already applied for this job");
        }

        Application application = Application.builder()
                .user(user)
                .job(job)
                .resumeUrl(request.getResumeUrl())
                .coverLetter(request.getCoverLetter())
                .status(ApplicationStatus.PENDING)
                .appliedAt(LocalDateTime.now())
                .build();

        Application savedApplication =
                applicationRepository.save(application);

        return ApplicationResponse.builder()
                .applicationId(savedApplication.getId())
                .message("Application submitted successfully")
                .jobTitle(job.getTitle())
                .company(job.getCompany())
                .status(savedApplication.getStatus().name())
                .appliedAt(savedApplication.getAppliedAt())
                .build();
    }

    @Override
    public List<ApplicationResponse> getMyApplications() {

        User user = userRepository.findByEmail("jayeshdhamal03@gmail.com")
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<Application> applications =
                applicationRepository.findByUser(user);

        return applications.stream()
                .map(application -> ApplicationResponse.builder()
                        .applicationId(application.getId())
                        .message("Application Found")
                        .jobTitle(application.getJob().getTitle())
                        .company(application.getJob().getCompany())
                        .status(application.getStatus().name())
                        .appliedAt(application.getAppliedAt())
                        .build())
                .toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsForJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        List<Application> applications =
                applicationRepository.findByJob(job);

        return applications.stream()
                .map(application -> ApplicationResponse.builder()
                        .applicationId(application.getId())
                        .message("Application Found")
                        .jobTitle(application.getJob().getTitle())
                        .company(application.getJob().getCompany())
                        .status(application.getStatus().name())
                        .appliedAt(application.getAppliedAt())
                        .build())
                .toList();
    }

    @Override
    public ApplicationResponse updateApplicationStatus(
            Long applicationId,
            ApplicationStatus status) {

        Application application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setStatus(status);

        Application updatedApplication =
                applicationRepository.save(application);

        return ApplicationResponse.builder()
                .applicationId(updatedApplication.getId())
                .message("Application status updated successfully")
                .jobTitle(updatedApplication.getJob().getTitle())
                .company(updatedApplication.getJob().getCompany())
                .status(updatedApplication.getStatus().name())
                .appliedAt(updatedApplication.getAppliedAt())
                .build();
    }
}