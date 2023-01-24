package com.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeViewController {
 
	// Showing the home page
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	// show contact page
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}

	// show sign-up page
	@RequestMapping("/signup")
	public String Signup() {
		return "SignUp";
	}

	// Show login page for Spring Security
	@RequestMapping("/login")
	public String login() {
		return "login"; 
	}

}
