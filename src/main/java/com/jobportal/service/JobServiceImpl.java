package com.jobportal.service;

import com.jobportal.dto.JobRequest;
import com.jobportal.dto.JobResponse;
import com.jobportal.entity.Job;
import com.jobportal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    /// this class will imlement all the methods that we declared in the interface class
    private final JobRepository jobRepository;
    // we have to imlpement the methods
    @Override
    public JobResponse createJob(JobRequest request)
    {
         // in this you are actually creating the job
          Job job = Job.builder().
                  title(request.getTitle()).company(request.getCompany()).
                  description(request.getDescription()).location(request.getLocation()).
                  salary(request.getSalary()).experienceRequired(request.getExperienceRequired()).
                  skills(request.getSkills()).jobType(request.getJobType()).createdAt(LocalDateTime.now()).build();

          // save into database
          Job savedJob = jobRepository.save(job);

          // now we have to convert the entity into -> response DTO
          return JobResponse.builder().id(savedJob.getId()).
                  title(savedJob.getTitle()).
                  company(savedJob.getCompany()).
                  description(savedJob.getDescription()).
                  location(savedJob.getDescription()).
                  salary(savedJob.getSalary()).
                  experienceRequired(savedJob.getExperienceRequired()).
                  skills(savedJob.getSkills()).
                  jobType(savedJob.getJobType()).
                  createdAt(savedJob.getCreatedAt()).build();


          // real flow :
          /* Job requestDTO -> use builder to create job entity -> save entity using repo
          -> get saved entity -> convert saved entity -> JobResponseDTO -> return response
           */


    }

    @Override
    public JobResponse updateJob(Long JobId, JobRequest request) {
        Job existingJob = jobRepository.findById(JobId).orElseThrow(() -> new RuntimeException("Job not found"));

        // update fields
        existingJob.setTitle(request.getTitle());
        existingJob.setCompany(request.getCompany());
        existingJob.setDescription(request.getDescription());
        existingJob.setLocation(request.getLocation());
        existingJob.setSalary(request.getSalary());
        existingJob.setExperienceRequired(request.getExperienceRequired());
        existingJob.setSkills(request.getSkills());
        existingJob.setJobType(request.getJobType());

        // we have to also update the timestamp
        existingJob.setUpdatedAt(LocalDateTime.now());

        // we have to save the updated job as well
        Job updatedJob = jobRepository.save(existingJob);

        // convert that entity -> responseDTO
        return JobResponse.builder().id(updatedJob.getId()).
                title(updatedJob.getTitle()).
                company(updatedJob.getCompany()).
                description(updatedJob.getDescription()).
                location(updatedJob.getLocation()).
                salary(updatedJob.getSalary()).
                experienceRequired(updatedJob.getExperienceRequired()).
                skills(updatedJob.getSkills()).jobType(updatedJob.getJobType()).
                createdAt(updatedJob.getCreatedAt()).
                recruiterEmail(updatedJob.getRecruiter().getEmail()).build();
    }

    @Override
    public void deleteJob(Long jobId) {
           Job existingJob = jobRepository.findById(jobId).orElseThrow(()-> new RuntimeException("Job not found "));
           jobRepository.delete(existingJob);


    }

    @Override
    public JobResponse getJobByID(Long jobId) {
          // this will be proper response function
          Job job = jobRepository.findById(jobId).orElseThrow(()-> new RuntimeException("Job not found"));

        return JobResponse.builder().id(job.getId()).
                title(job.getTitle()).
                company(job.getCompany()).
                description(job.getDescription()).
                location(job.getLocation()).
                salary(job.getSalary()).
                experienceRequired(job.getExperienceRequired()).
                skills(job.getSkills()).jobType(job.getJobType()).
                createdAt(job.getCreatedAt()).
                recruiterEmail(job.getRecruiter().getEmail()).build();

    }

    @Override
    public List<JobResponse> getAllJobs() {
            List<Job> jobs = jobRepository.findAll();

            // again we have to convert into response dto
            return jobs.stream().map(job->JobResponse.builder().id(job.getId()).title(job.getTitle()).
                    company(job.getCompany()).description(job.getDescription()).
                    location(job.getLocation()).salary(job.getSalary()).
                    experienceRequired(job.getExperienceRequired()).
                    skills(job.getSkills()).
                    jobType(job.getJobType()).
                    createdAt(job.getCreatedAt()).
                    recruiterEmail(job.getRecruiter().getEmail()).build()).toList();
    }


}

