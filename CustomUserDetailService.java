package com.example.SkillCore.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SkillCore.Models.User;
import com.example.SkillCore.Repository.Userrepo;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
    private Userrepo userRepo;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 User user =new User();
				 userRepo.findByEmail(user.getEmail())
	                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " +user.getEmail() ));

		 CustomUserDetail cud= new CustomUserDetail(user);
	        return cud;
	}

}
