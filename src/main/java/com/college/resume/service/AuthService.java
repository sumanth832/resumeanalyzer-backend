package com.college.resume.service;
import com.college.resume.dto.AuthDtos.*;
import com.college.resume.model.*;
import com.college.resume.repo.UserRepository;
import com.college.resume.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
 private final UserRepository users;private final PasswordEncoder encoder;private final JwtService jwt;
 public AuthService(UserRepository users,PasswordEncoder encoder,JwtService jwt){this.users=users;this.encoder=encoder;this.jwt=jwt;}
 public AuthResponse register(RegisterRequest r){
  String email=r.email().trim().toLowerCase();if(users.existsByEmailIgnoreCase(email))throw new IllegalArgumentException("An account with this email already exists.");
  AppUser u=users.save(new AppUser(email,encoder.encode(r.password()),r.fullName().trim(),Role.STUDENT));return response(u);
 }
 public AuthResponse login(LoginRequest r){AppUser u=users.findByEmailIgnoreCase(r.email().trim()).orElseThrow(()->new IllegalArgumentException("Invalid email or password."));
  if(!encoder.matches(r.password(),u.getPasswordHash()))throw new IllegalArgumentException("Invalid email or password.");return response(u);}
 private AuthResponse response(AppUser u){return new AuthResponse(jwt.generate(u.getEmail(),u.getRole().name()),"Bearer",u.getId(),u.getFullName(),u.getEmail(),u.getRole().name());}
}
