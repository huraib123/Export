package com.example.SkillCore.Controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SkillCore.Models.PasswordHandling;
import com.example.SkillCore.Models.User;
import com.example.SkillCore.Repository.Userrepo;

@RestController
@RequestMapping("/api/auth")
public class ChangePasswordController {
	
	private Userrepo ur;
	
	private PasswordEncoder passenco;
	
	@PostMapping("/change")
	
	public ResponseEntity<?> changepassword(@RequestBody PasswordHandling request,Authentication auth){
		
		String username=auth.getName();
		
		Optional<User> opt=ur.findByEmail(username);
		
		if(opt.isEmpty()) {
			return ResponseEntity.status(404).body("user not found");
		}
		
		User user=opt.get();
		if(!passenco.matches(request.getOldPassword(),user.getPassword())) {
			return ResponseEntity.status(400).body("Old password is incorrect");
		}
		
		user.setPassword(passenco.encode(request.getNewPassword()));
		ur.save(user);
		
		return ResponseEntity.ok("Password changed successfully");
		 
	}

}
