package com.jobportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SkillsEmployee 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empId")
	private RecruiterEmployeeDetails empId;
	
	private String skills ;
	
	public RecruiterEmployeeDetails getEmpId() {
		return empId;
	}

	public void setEmpId(RecruiterEmployeeDetails empId) {
		this.empId = empId;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	
	
	
}
