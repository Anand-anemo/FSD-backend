package com.kitchenStory.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kitchenStory.entity.Role;
import com.kitchenStory.entity.User;
import com.kitchenStory.repository.Rolerepo;
import com.kitchenStory.repository.Userrepo;

@Service
public class UserService {
	@Autowired
	private Userrepo userrepo;
	@Autowired
	private Rolerepo rolerepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User registerNewUser(User user) {
		
		Role role = rolerepo.findById("User").get();
		
		Set<Role> roles =  new HashSet<>();
		roles.add(role);
		user.setRole(roles);
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		return userrepo.save(user);
		}
	
	public void initRolesAndUser() {
		//Role class.....
		Role adminRole=new Role();
		
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Admin Role");
		rolerepo.save(adminRole);
		
        Role userRole=new Role();
		
		userRole.setRoleName("User");
		userRole.setRoleDescription("default role for new user");
		rolerepo.save(userRole);
		
		//User class.....
		
		User adminUser = new User();
		adminUser.setUserFirstName("Anand S");
		adminUser.setUserLastName("Rawat");
		adminUser.setUserName("admin101");
		adminUser.setUserPassword(getEncodedPassword("heloo"));
		Set<Role> adminRoles=new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRole(adminRoles);
		userrepo.save(adminUser);
		
	User user = new User();
		user.setUserFirstName("Hemant");		
		user.setUserLastName("Singh");		
		user.setUserName("friend");
		user.setUserPassword(getEncodedPassword("freedom"));
		Set<Role> userRoles=new HashSet<>();
		userRoles.add(userRole);
		user.setRole(userRoles);
		userrepo.save(user);
		
		
	}
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

}
