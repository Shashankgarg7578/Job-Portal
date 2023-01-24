package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.model.ApplyJobs;

public interface ApplyJobRepo  extends JpaRepository<ApplyJobs, Integer>
{
	 
}
