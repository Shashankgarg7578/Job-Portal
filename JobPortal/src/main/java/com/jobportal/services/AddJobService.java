package com.jobportal.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jobportal.model.AddJob;
import com.jobportal.repository.AddJobRepo;

@Service
public class AddJobService {

	@Autowired
	private AddJobRepo addJobRepo;

	// this is used for Recruiter to add particular job
	public AddJob saveJob(AddJob addjobs) {
		return this.addJobRepo.save(addjobs);
	}

	// used for getting the all jobs
	public List<AddJob> getJobList() {
		List<AddJob> jobList = new ArrayList<AddJob>();
		for (AddJob addJob : addJobRepo.findAll()) {
			jobList.add(addJob);
		}
		return jobList;
	}

	// use for Delete particular job by Recruiter
	public void deleteJobById(int id) {
		addJobRepo.deleteById(id);
	}

	public List<AddJob> getjobByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return addJobRepo.getjobByKeyword(keyword);
	}

	// used for getting the all jobs
	public AddJob getJobByJobId(int id) {
		AddJob job = new AddJob();
		for (AddJob addJob : addJobRepo.findAll()) {
			if (id == addJob.getId()) {
				job.setId(addJob.getId());
				job.setCity(addJob.getCity());
				job.setCompanyName(addJob.getCompanyName());
				job.setDate(addJob.getDate());
				job.setDescription(addJob.getDescription());
				job.setJobSkills(addJob.getJobSkills());
			}
		}
		return job;
	}

}
