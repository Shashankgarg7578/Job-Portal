package com.jobportal.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.config.CustomRecruiterEmployeeDetails;
import com.jobportal.model.AddJob;
import com.jobportal.model.RecruiterEmployeeDetails;
import com.jobportal.services.AddJobService;
import com.jobportal.services.ApplyJobService;
import com.jobportal.services.RecruiterEmployeeDetailsService;
import com.jobportal.services.SkillsEmployeeService;

@RestController
public class EmployeeRestController {
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/EmpResume";

	@Autowired
	private AddJobService addJobService;

	@Autowired
	private ApplyJobService applyJobService;

	@Autowired
	private RecruiterEmployeeDetailsService recruiterEmployeeDetailsService;

	@Autowired
	private SkillsEmployeeService skillsEmployeeService;

	@GetMapping("/Employee/getJobs")
	public List<AddJob> getJobsbyKeyword(@RequestParam String keyword, Authentication principal) {
		List<AddJob> listAlljobs = addJobService.getjobByKeyword(keyword);

		// List of all jobsIDs only in object list
		List<Integer> allJobIds = new ArrayList<Integer>();
		for (AddJob ji : listAlljobs) {
			allJobIds.add(ji.getId());
		}

		// used for getting Id of currently login employee
		CustomRecruiterEmployeeDetails accountantDetails = (CustomRecruiterEmployeeDetails) principal.getPrincipal();
		int employeeId = accountantDetails.getId();

		// list of jobsIDs which currenty login User Applied
		List<Integer> listOfJobApplied = applyJobService.getjobIdsOfLoginEmp(employeeId);

		// List of JobIds which currently login user not applied
		List<Integer> notAppliedIds = new ArrayList<Integer>();

		for (Integer jobid : allJobIds) {
			if (listOfJobApplied.contains(jobid)) {

			} else {
				notAppliedIds.add(jobid);
			}
		}

		// compare ListofAll JobsId to currently loginUserAppliedJobIds for confirmation
		// (User already apply for job or not)
		for (Integer jobid : allJobIds) {
			if (listOfJobApplied.contains(jobid)) {

			} else {
				notAppliedIds.add(jobid);
				break;
			}
			break;
		}

		// this is the All jobs which currently login user not applied
		List<AddJob> notAppliedJobsList = new ArrayList<AddJob>();

		for (Integer notapplies : notAppliedIds) {
			notAppliedJobsList.add(addJobService.getJobByJobId(notapplies));
		}

		return notAppliedJobsList;
	}

	// this is used for apply jobs in employee module
	@PostMapping("/Employee/applyJobs/{id}")
	public void applyJobs(@PathVariable("id") int id, Authentication principal) {
		CustomRecruiterEmployeeDetails accountantDetails = (CustomRecruiterEmployeeDetails) principal.getPrincipal();

		if (accountantDetails != null) {
			int employeeId = accountantDetails.getId();
			boolean cheack = applyJobService.checkApplyJob(employeeId, id);
			if (cheack == false) {
				applyJobService.applyJob(id, employeeId);
			}
		}
	}

	// this is used to update the employee profile
	@PostMapping(value = "/Employee/EmpProfile")
	public void empProfile(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("role") String role, @RequestParam("resumeName") String resumeName,
			@RequestParam("resumePdf") MultipartFile resumePdf, @RequestParam("skills") String skill,
			@RequestParam("dob") Date dob, @RequestParam("gender") String gender, @RequestParam("phone") long phone,
			@RequestParam("city") String city) {
		try {
			System.out.println(skill);
			RecruiterEmployeeDetails emp = new RecruiterEmployeeDetails();
			emp.setId(id);
			emp.setName(name);
			emp.setEmail(email);
			emp.setPassword(password);
			emp.setRole(role);
			emp.setDob(dob);
			emp.setGender(gender);
			emp.setPhone(phone);
			emp.setCity(city);

			String imageUUID;

			// for storing image in project folder
			if (!resumePdf.isEmpty()) {
				imageUUID = resumePdf.getOriginalFilename();
				Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
				Files.write(fileNameAndPath, resumePdf.getBytes());
			} else {
				imageUUID = resumeName;
			}
			emp.setResumeName(imageUUID);

			// Save the skills details on another Skills table

			// used for get all skills by id
			List<String> skillsById = skillsEmployeeService.getAllSkillsByID(id);

			String skils[] = skill.split(",");

			// List list = Arrays.asList(skils);

			for (int i = 0; i < skils.length; i++) {

				// check skill already stored or not
				boolean cheak = false;
				for (String s : skillsById) {
					if (s.equals(skils[i])) {
						cheak = true;
					}
				}

				// if skill already not stored then add skill
				if (cheak == false) {

					skillsEmployeeService.addSkill(id, skils[i]);
				}

			}

			// save all details of employee profile
			recruiterEmployeeDetailsService.saveRecruiterEmployee(emp);
		} catch (Exception e) {
			System.out.println("please fill all the fields properly");
		}
	}

}