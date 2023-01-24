package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jobportal.config.CustomRecruiterEmployeeDetails;
import com.jobportal.model.RecruiterEmployeeDetails;
import com.jobportal.services.RecruiterEmployeeDetailsService;

@Controller
@RequestMapping("/Employee")
public class EmployeeViewController {
	@Autowired
	private RecruiterEmployeeDetailsService recruiterEmployeeDetailsService;

	// used for employee home page
	@RequestMapping("/EmployeeHome")
	private String emphome() {
		return "Employee/EmployeeHomePage";
	}

	// used for view Jobs page
	@RequestMapping("/viewJobs")
	private String viewJobs(Model model) {
		return "Employee/viewJobs";
	}

	// used for show employee profile
	@RequestMapping("/EmpProfile")
	private String EmpProfile(Authentication principal, Model model) {
		CustomRecruiterEmployeeDetails accountantDetails = (CustomRecruiterEmployeeDetails) principal.getPrincipal();

		if (accountantDetails != null) {
			int employeeId = accountantDetails.getId();
			RecruiterEmployeeDetails emp = recruiterEmployeeDetailsService.getOneEmployee(employeeId);
			model.addAttribute("emp", emp);
		}
		return "Employee/EmpProfile";
	}

}
