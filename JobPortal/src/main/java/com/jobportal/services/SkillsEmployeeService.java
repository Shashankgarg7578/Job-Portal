package com.jobportal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jobportal.model.SkillsEmployee;
import com.jobportal.repository.RecruiterEmployeeDetailsRepo;
import com.jobportal.repository.SkillsEmployeeRepo;

@Service
public class SkillsEmployeeService {
	@Autowired
	private SkillsEmployeeRepo skillsEmployeeRepo;

	@Autowired
	private RecruiterEmployeeDetailsRepo recruiterEmployee;

	// this is used to add skill for a user One skill at a time
	public void addSkill(int employeeId, String skill) {
		SkillsEmployee skills = new SkillsEmployee();

		skills.setEmpId(recruiterEmployee.getById(employeeId));
		skills.setSkills(skill);

		this.skillsEmployeeRepo.save(skills);
	}

	// used for get all skills
	public List<String> getAllSkillsByID(int id) {
		List<String> skillsById = new ArrayList<String>();

		for (SkillsEmployee skil : skillsEmployeeRepo.findAll()) {
			if (skil.getEmpId().getId() == recruiterEmployee.getById(id).getId()) {
				skillsById.add(skil.getSkills());
			}

		}

		return skillsById;
	}

}
