package com.jobportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jobportal.model.RecruiterEmployeeDetails;
import com.jobportal.repository.RecruiterEmployeeDetailsRepo;

public class RecruiterEmployeeDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private RecruiterEmployeeDetailsRepo recruiterEmployeeDetailsRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// fetching Accountant from database
		RecruiterEmployeeDetails recruiterEmployeeDetails = recruiterEmployeeDetailsRepo.getOneEmpRecByEmail(username);

		if (recruiterEmployeeDetails == null) {
			throw new UsernameNotFoundException("Could not find employee");
		}

		CustomRecruiterEmployeeDetails customAccountantDetails = new CustomRecruiterEmployeeDetails(
				recruiterEmployeeDetails);

		return customAccountantDetails;
	}

}
