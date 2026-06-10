package com.jobportal.repository;

import com.jobportal.entity.Application;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // check if user already applied for a job
    boolean existsByUserAndJob(User user, Job job);

    // get all applications of a user
    List<Application> findByUser(User user);

    // get all appliations for particular job
    List<Application> findByJob( Job job);
}
