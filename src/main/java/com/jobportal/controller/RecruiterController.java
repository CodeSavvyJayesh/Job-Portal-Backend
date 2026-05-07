package com.jobportal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {
    @GetMapping("/dashboard")
    public String recruiterDashboard()
    {
        return "Welcome recruiter";
    }

}
