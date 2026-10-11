package com.college.resume.dto;
import jakarta.validation.constraints.*;
public class AuthDtos {
 public record RegisterRequest(@NotBlank @Size(max=120) String fullName,@Email @NotBlank @Size(max=180) String email,@NotBlank @Size(min=8,max=72) String password){}
 public record LoginRequest(@Email @NotBlank String email,@NotBlank String password){}
 public record AuthResponse(String token,String tokenType,Long id,String fullName,String email,String role){}
 public record UserResponse(Long id,String fullName,String email,String role){}
}
