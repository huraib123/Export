package com.example.SkillCore.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SkillCore.Models.User;
import com.example.SkillCore.Repository.Userrepo;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final Userrepo userRepo;

    public UserController(Userrepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userRepo.existsById(id)) return ResponseEntity.notFound().build();

        userRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    
}
