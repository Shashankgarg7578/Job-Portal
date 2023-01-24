package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jobportal.model.RecruiterEmployeeDetails;
import com.jobportal.services.RecruiterEmployeeDetailsService;

@RestController
public class HomeRestController {
	@Autowired
	private RecruiterEmployeeDetailsService RecruiterEmployeeService;

	// save Recruiter and Employee with the help of role
	@PostMapping("/saveByRole")
	public RecruiterEmployeeDetails adddDetails(@RequestBody RecruiterEmployeeDetails details) 
	{
		boolean cheack = RecruiterEmployeeService.findbyEmail(details.getEmail());
       
		if (cheack == true) {
			details = null;
			return details;
		} else {
			
			RecruiterEmployeeService.saveRecruiterEmployee(details);
			return details;
		}
	}
}