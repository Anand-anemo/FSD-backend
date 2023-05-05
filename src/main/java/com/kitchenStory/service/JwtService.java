package com.kitchenStory.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kitchenStory.entity.JwtRequest;
import com.kitchenStory.entity.JwtResponse;
import com.kitchenStory.entity.User;
import com.kitchenStory.jwthandler.jwtHandler;
import com.kitchenStory.repository.Userrepo;

@Service
public class JwtService implements UserDetailsService{
	@Autowired
	private Userrepo userRepo;
	
	@Autowired
	private jwtHandler jwthandler;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken (JwtRequest jwtRequest ) throws Exception{
		String userName= jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
	    authenticate(userName, userPassword);
	    
	   final UserDetails userDetails = loadUserByUsername(userName);
	   String newGeneratedToken = jwthandler.genrateToken(userDetails);
	   User user = userRepo.findById(userName).get();
	   return new JwtResponse(user , newGeneratedToken);

		}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findById(username).get();
		
		if(user!=null) {
			return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
		} else {
            throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
	}
	
    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
    
	private void authenticate(String userName , String userPassword) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
		
	}catch(DisabledException e) {
		throw new Exception("user is disabled" , e);
	}catch(BadCredentialsException e) {
		throw new Exception("Bad credentials",e);
	}
	}

}
