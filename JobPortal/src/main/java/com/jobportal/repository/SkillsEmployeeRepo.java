package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jobportal.model.SkillsEmployee;
 
public interface SkillsEmployeeRepo extends JpaRepository<SkillsEmployee, Integer> 
{
	@Query("SELECT s from SkillsEmployee s WHERE s.empId =?1")
    public List<SkillsEmployee> getSkillsByEmpId(int id); 
}
