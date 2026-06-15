package com.jobportal.service;

import com.jobportal.dto.JobRequest;
import com.jobportal.dto.JobResponse;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    @Override
    public JobResponse createJob(JobRequest request) {

        User recruiter = getCurrentUser();

        Job job = Job.builder()
                .title(request.getTitle())
                .company(request.getCompany())
                .description(request.getDescription())
                .location(request.getLocation())
                .salary(request.getSalary())
                .experienceRequired(request.getExperienceRequired())
                .skills(request.getSkills())
                .jobType(request.getJobType())
                .createdAt(LocalDateTime.now())
                .recruiter(recruiter)
                .build();

        Job savedJob = jobRepository.save(job);

        return JobResponse.builder()
                .id(savedJob.getId())
                .title(savedJob.getTitle())
                .company(savedJob.getCompany())
                .description(savedJob.getDescription())
                .location(savedJob.getLocation())
                .salary(savedJob.getSalary())
                .experienceRequired(savedJob.getExperienceRequired())
                .skills(savedJob.getSkills())
                .jobType(savedJob.getJobType())
                .createdAt(savedJob.getCreatedAt())
                .recruiterEmail(savedJob.getRecruiter().getEmail())
                .build();
    }

    @Override
    public JobResponse updateJob(Long jobId, JobRequest request) {

        Job existingJob = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        existingJob.setTitle(request.getTitle());
        existingJob.setCompany(request.getCompany());
        existingJob.setDescription(request.getDescription());
        existingJob.setLocation(request.getLocation());
        existingJob.setSalary(request.getSalary());
        existingJob.setExperienceRequired(request.getExperienceRequired());
        existingJob.setSkills(request.getSkills());
        existingJob.setJobType(request.getJobType());
        existingJob.setUpdatedAt(LocalDateTime.now());

        Job updatedJob = jobRepository.save(existingJob);

        return JobResponse.builder()
                .id(updatedJob.getId())
                .title(updatedJob.getTitle())
                .company(updatedJob.getCompany())
                .description(updatedJob.getDescription())
                .location(updatedJob.getLocation())
                .salary(updatedJob.getSalary())
                .experienceRequired(updatedJob.getExperienceRequired())
                .skills(updatedJob.getSkills())
                .jobType(updatedJob.getJobType())
                .createdAt(updatedJob.getCreatedAt())
                .recruiterEmail(updatedJob.getRecruiter().getEmail())
                .build();
    }

    @Override
    public void deleteJob(Long jobId) {

        Job existingJob = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        jobRepository.delete(existingJob);
    }

    @Override
    public JobResponse getJobByID(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .company(job.getCompany())
                .description(job.getDescription())
                .location(job.getLocation())
                .salary(job.getSalary())
                .experienceRequired(job.getExperienceRequired())
                .skills(job.getSkills())
                .jobType(job.getJobType())
                .createdAt(job.getCreatedAt())
                .recruiterEmail(job.getRecruiter().getEmail())
                .build();
    }

    @Override
    public List<JobResponse> getAllJobs() {

        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(job -> JobResponse.builder()
                        .id(job.getId())
                        .title(job.getTitle())
                        .company(job.getCompany())
                        .description(job.getDescription())
                        .location(job.getLocation())
                        .salary(job.getSalary())
                        .experienceRequired(job.getExperienceRequired())
                        .skills(job.getSkills())
                        .jobType(job.getJobType())
                        .createdAt(job.getCreatedAt())
                        .recruiterEmail(job.getRecruiter().getEmail())
                        .build())
                .toList();
    }
}