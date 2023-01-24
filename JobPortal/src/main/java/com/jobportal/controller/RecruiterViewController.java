package com.jobportal.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jobportal.model.RecruiterEmployeeDetails;
import com.jobportal.services.ApplyJobService;
import com.jobportal.services.RecruiterEmployeeDetailsService;

@Controller
@RequestMapping("/Recruiter")
public class RecruiterViewController {
	@Autowired
	private ApplyJobService applyJobService;

	@Autowired
	private RecruiterEmployeeDetailsService empService;

	// this is used for show Recruiter home page
	@RequestMapping("/RecruiterHomePage")
	public String RecruiterHomePage() {
		return "Recruiter/RecruiterHomePage";
	}

	// used for show add job page in which recruiter can add a particular job
	@RequestMapping("/addjob")
	public String Addjob() {
		return "Recruiter/AddJob";
	}

	// this is used for get Job list Page
	@RequestMapping("/joblist")
	public String joblist() {
		return "Recruiter/joblist";
	}

	// this is used for get Applier on particular Job so this is used for getting
	// information of Appliers
	@GetMapping("/getAppliers/{id}")
	public String getAppliersList(@PathVariable int id, @Param("keyword") String keyword, Model model) {
		if (keyword == null) {
			List<RecruiterEmployeeDetails> employes = new ArrayList<RecruiterEmployeeDetails>();

			List<Integer> appliersEmpIds = applyJobService.getAppliersID(id);

			for (Integer ids : appliersEmpIds) {
				employes.add(empService.getOneEmployee(ids));
			}

			model.addAttribute("employes", employes);

			return "Recruiter/showAppliers";

		} else {
			List<RecruiterEmployeeDetails> employes = new ArrayList<RecruiterEmployeeDetails>();

			List<Integer> appliersEmpIds = applyJobService.getAppliersID(id);

			employes.addAll(empService.getApplierByKeyword(appliersEmpIds, keyword));

			model.addAttribute("employes", employes);

			return "Recruiter/showAppliers";

		}
	}

}
