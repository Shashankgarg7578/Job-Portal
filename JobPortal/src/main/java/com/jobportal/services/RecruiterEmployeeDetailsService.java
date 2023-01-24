package com.jobportal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.model.RecruiterEmployeeDetails;
import com.jobportal.repository.RecruiterEmployeeDetailsRepo;

@Service
public class RecruiterEmployeeDetailsService {

	@Autowired
	private RecruiterEmployeeDetailsRepo RecruiterEmployeeRepo;

	//save the SignUp page data
	public RecruiterEmployeeDetails saveRecruiterEmployee(RecruiterEmployeeDetails RecruiterEmployeeDetails) 
	{
		return this.RecruiterEmployeeRepo.save(RecruiterEmployeeDetails);
	}

	
	//this is used for get One employee details 
	 @SuppressWarnings("deprecation")
	public RecruiterEmployeeDetails getOneEmployee(int id)
	 {
	   return this.RecruiterEmployeeRepo.getOne(id);
	 }
	
	//this is used for find one employee by their ID
	public boolean findbyEmail(String email) 
	{
        boolean cheackDetails = false;
		
        for (RecruiterEmployeeDetails details : RecruiterEmployeeRepo.findAll()) 
		{
			if (email.equals(details.getEmail())) 
			{
				cheackDetails = true;
			}
		}
		
		return cheackDetails;
	}
	
	//get appliers with the help of keyword
	public List<RecruiterEmployeeDetails> getApplierByKeyword(List<Integer> appliersEmpIds, String keyword)
	{
		return RecruiterEmployeeRepo.getApplierByKeyword(appliersEmpIds,"%"+keyword+"%");
	}
	
	

}
