package com.jobportal.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class AddJob implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3886813986241145322L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String companyName;
	
	private String jobSkills; 
	
	private Date date;
	private String city;
	private String description;

	public int getId() {
		return id;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getJobSkills() {
		return jobSkills;
	}

	public void setJobSkills(String jobSkills) {
		this.jobSkills = jobSkills;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AddJob [id=" + id + ", companyName=" + companyName + ", jobSkills=" + jobSkills + ", date=" + date
				+ ", city=" + city + ", description=" + description + "]";
	}

	
	
}
