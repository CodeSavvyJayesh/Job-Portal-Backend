package com.jobportal.service;

import com.jobportal.dto.JobRequest;
import com.jobportal.dto.JobResponse;
import com.jobportal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JobService  {
      // this is basically  interface class
       JobResponse createJob(JobRequest request);
       JobResponse updateJob(Long JobId,JobRequest request);
       void deleteJob(Long jobId);

       // to get single job details
       JobResponse getJobByID(Long jobId);

       // to get all the jobs
       List<JobResponse> getAllJobs();
}
