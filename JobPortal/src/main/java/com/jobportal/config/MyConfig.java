package com.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter
{
   
	@Bean
	public UserDetailsService getUserDetailsService()
	{
		return new RecruiterEmployeeDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
	    daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
	
	    return daoAuthenticationProvider;
	}

	//COnfigure method
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
	    auth.authenticationProvider(authenticationProvider());   
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
	
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/Employee/**").hasAnyAuthority("EMPLOYEE")
		.antMatchers("/Recruiter/**").hasAnyAuthority("RECRUITER")
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login")
		.permitAll()
		.and().logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/403")
		;
		
	} 
	
	
}
