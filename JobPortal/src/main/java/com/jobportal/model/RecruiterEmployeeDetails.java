package com.jobportal.model;

import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;


import org.springframework.web.multipart.MultipartFile;

@Entity
public class RecruiterEmployeeDetails 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    
	private String name;
	
	@Column(unique = true)
	@Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
	private String email;
	private String password;
	
	@Column(name = "ROLE")
	private String role;
	
	private String resumeName;
	
	
	@OneToMany(mappedBy = "empId", cascade = CascadeType.ALL)
	private List<SkillsEmployee> skillsEmployee;
	
	@Transient
	private MultipartFile resumePdf;
	
	
	private Date dob;
	private String gender;
	private long phone;
	private String city;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	
	public List<SkillsEmployee> getSkillsEmployee() {
		return skillsEmployee;
	}
	public void setSkillsEmployee(List<SkillsEmployee> skillsEmployee) {
		this.skillsEmployee = skillsEmployee;
	}
	public MultipartFile getResumePdf() {
		return resumePdf;
	}
	public void setResumePdf(MultipartFile resumePdf) {
		this.resumePdf = resumePdf;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "RecruiterEmployeeDetails [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", role=" + role + ", resumeName=" + resumeName + ", skillsEmployee=" + skillsEmployee
				+ ", resumePdf=" + resumePdf + ", dob=" + dob + ", gender=" + gender + ", phone=" + phone + ", city="
				+ city + "]";
	}
	
	
	
}
