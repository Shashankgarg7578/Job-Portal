package com.jobportal.controller;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jobportal.model.AddJob;
import com.jobportal.services.AddJobService;

@RestController
public class RecruiterRestController {
	@Autowired
	private AddJobService addJobService;
   
	//used for save particular job 
	@PostMapping("/Recruiter/saveJob")
	private AddJob addjob(@RequestParam("companyName") String companyName
			,@RequestParam("jobSkills") String jobSkills,@RequestParam("date") Date date
			,@RequestParam("city") String city,@RequestParam("description") String description)
	{
		AddJob addjob = new AddJob();
		addjob.setCompanyName(companyName);
		addjob.setJobSkills(jobSkills);
		addjob.setDate(date);
		addjob.setCity(city);
		addjob.setDescription(description);
		addJobService.saveJob(addjob);
		
		return addjob;
	}
	
	//used for save particular job 
		@PostMapping("/Recruiter/updateJob")
		private AddJob updateJob(@RequestBody AddJob addjob) {
			addJobService.saveJob(addjob);
			return addjob;
		}


	//used for get all job list in recruiter module
	@GetMapping("/Recruiter/jobList")
	private List<AddJob> getJobs(@RequestParam String keyword) {
		if(keyword == null) {		
			List<AddJob> listAlljobs = addJobService.getJobList();
     		return listAlljobs;
		}else {	
     		List<AddJob> listAlljobs = addJobService.getjobByKeyword(keyword);
			return listAlljobs;
		}
	}

	//used for Delete particular Job with the help of ID
	@RequestMapping(method = RequestMethod.DELETE, value = "/Recruiter/deleteJob/{id}")
	public void deleteJobById(@PathVariable int id) {
		addJobService.deleteJobById(id);
	}
	
}
