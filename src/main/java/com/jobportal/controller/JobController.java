package com.jobportal.controller;

import com.jobportal.dto.JobRequest;
import com.jobportal.dto.JobResponse;
import com.jobportal.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor    // this is necesasry so that we dont need to create the constructor
@RequestMapping("/api/recruiter/jobs")
public class JobController {
    // this class wll be responsible for frontend backend conmmuication
    // in this controller class we actually call the service layer class
    // basically we have to think about the api endpoints
    // post api/recruiter/jobs : recruiter will create the job
    // post api/recruiter/job/{jobID}  : recruiter will update the job
    // post api/recruiter/job/{jobId} : this will delete the job
    // get api/jobs/{jobid}   : this will basically return only single job
    // to get all job : get/api/job

    private final JobService jobService;

    @PostMapping
    public JobResponse createJob(@RequestBody JobRequest request)  // basically request body converts
            // frontend json-> DTO automatically
    {
        return jobService.createJob(request);
    }
    // update the job
    @PutMapping("/{jobId}")
    public JobResponse updateJob(@PathVariable Long jobId, @RequestBody JobRequest request)
    {
         return jobService.updateJob(jobId,request);
    }
    @DeleteMapping("/{jobId}")
    public String deleteJob(@PathVariable Long jobId)
    {
         jobService.deleteJob(jobId);

         return "Job deleted Successfully";
    }

    // get job by id
    @GetMapping("/{jobId}")
    public JobResponse getJobById(@PathVariable Long jobId)
    {
         return jobService.getJobByID(jobId);
    }
    @GetMapping
    public List<JobResponse> getAllJobs(){
        return jobService.getAllJobs();
    }




}
