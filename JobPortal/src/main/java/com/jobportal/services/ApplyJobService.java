package com.jobportal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jobportal.model.ApplyJobs;
import com.jobportal.repository.ApplyJobRepo;

@Service
public class ApplyJobService 
{
    @Autowired
	private ApplyJobRepo applyJobRepo;	
	
    
    
    //this is used for add Appliers in aplly table with there employee id and job id
	public void applyJob(int id, int employeeId)
	{
		ApplyJobs apply = new ApplyJobs();
		apply.setJobId(id);
		apply.setEmpId(employeeId);
	   
		this.applyJobRepo.save(apply);
	}

	
	//this is used for cheack particular user already apply on a job or not
	public boolean checkApplyJob(int employeeId, int id)
	{
		 boolean cheackDetails = false;
	
		for(ApplyJobs cheack  :applyJobRepo.findAll())
		{
			if(employeeId == cheack.getEmpId() && id == cheack.getJobId())
			{
				cheackDetails  = true;
				return cheackDetails;
			}
		}
		return false;
	}

	//this is used for getting employer id's who apply for a particular job
	public List<Integer> getAppliersID(int jobId)
	{
	     List<Integer> appliersIDs = new ArrayList<Integer>();
		
	     for(ApplyJobs jobIds : applyJobRepo.findAll())
	     {
	    	 if(jobId == jobIds.getJobId())
	    	 {
	    		 appliersIDs.add(jobIds.getEmpId());
	    	 }
	     }

	     return appliersIDs;
	}

	//get jobids of currently login employee
	public List<Integer> getjobIdsOfLoginEmp(int employeeId)
	{
		 List<Integer> empJobIds = new ArrayList<Integer>();
		   
		    
		    List<ApplyJobs> jobs = applyJobRepo.findAll();
		    
		    for(ApplyJobs ids : jobs)
		    {
		    	if(employeeId == ids.getEmpId())
		    	{
		    		empJobIds.add(ids.getJobId());
		    	}
		    }
		    
		    
		return empJobIds;
	}
	
	public List<ApplyJobs> getList()
	{
		return applyJobRepo.findAll();
	}
	
		
}
