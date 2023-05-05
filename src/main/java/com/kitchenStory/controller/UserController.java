package com.kitchenStory.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kitchenStory.entity.User;
import com.kitchenStory.service.UserService;

@RestController
public class UserController {
	@Autowired
	 private UserService userservice;
	
	//method to get data for table;,,,,,
	@PostConstruct
	public void initRolesAndUser() {
		userservice.initRolesAndUser();
	}
	@PostMapping({"/registerNewUser"})
	public User registerNewUser(@RequestBody User user) {
		return userservice.registerNewUser(user);
	}
	
	@GetMapping({"/forAdmin"})
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		
		return "only for admin";
	}
	
	@GetMapping({"/forUser"})
	@PreAuthorize("hasRole('User')")
	public String forUser() {
		return "only for users";
	}

}
