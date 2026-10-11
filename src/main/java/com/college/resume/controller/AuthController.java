package com.college.resume.controller;
import com.college.resume.dto.AuthDtos.*;
import com.college.resume.model.AppUser;
import com.college.resume.repo.UserRepository;
import com.college.resume.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AuthService auth;private final UserRepository users;
 public AuthController(AuthService auth,UserRepository users){this.auth=auth;this.users=users;}
 @PostMapping("/register") public AuthResponse register(@Valid @RequestBody RegisterRequest r){return auth.register(r);}
 @PostMapping("/login") public AuthResponse login(@Valid @RequestBody LoginRequest r){return auth.login(r);}
 @GetMapping("/me") public UserResponse me(Authentication a){AppUser u=users.findByEmailIgnoreCase(a.getName()).orElseThrow();return new UserResponse(u.getId(),u.getFullName(),u.getEmail(),u.getRole().name());}
}
