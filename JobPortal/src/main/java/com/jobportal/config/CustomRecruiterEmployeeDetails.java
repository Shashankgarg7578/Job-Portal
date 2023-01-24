package com.jobportal.config;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jobportal.model.RecruiterEmployeeDetails;

@SuppressWarnings("serial")
public class CustomRecruiterEmployeeDetails implements UserDetails {

	private RecruiterEmployeeDetails recruiterEmployeeDetails;

	public CustomRecruiterEmployeeDetails(RecruiterEmployeeDetails recruiterEmployeeDetails) {
		super();
		this.recruiterEmployeeDetails = recruiterEmployeeDetails;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(recruiterEmployeeDetails.getRole());
		return List.of(authority);
	}

	public int getId()
	{
		return recruiterEmployeeDetails.getId();
	}
	
	@Override
	public String getPassword() {
		return recruiterEmployeeDetails.getPassword();
	}

	@Override
	public String getUsername() {
		return recruiterEmployeeDetails.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
