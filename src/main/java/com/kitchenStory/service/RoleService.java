package com.kitchenStory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchenStory.entity.Role;
import com.kitchenStory.repository.Rolerepo;

@Service
public class RoleService {
	@Autowired
	private Rolerepo rolerepo;
	
	public Role createNewRole(Role role) {
		
		return rolerepo.save(role);
		
	}

}
