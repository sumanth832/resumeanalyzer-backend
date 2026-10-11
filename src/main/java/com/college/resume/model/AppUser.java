package com.college.resume.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="app_users")
public class AppUser {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, unique=true, length=180) private String email;
 @Column(nullable=false) private String passwordHash;
 @Column(nullable=false, length=120) private String fullName;
 @Enumerated(EnumType.STRING) @Column(nullable=false, length=30) private Role role=Role.STUDENT;
 @Column(nullable=false, updatable=false) private Instant createdAt=Instant.now();
 protected AppUser() {}
 public AppUser(String email,String passwordHash,String fullName,Role role){this.email=email;this.passwordHash=passwordHash;this.fullName=fullName;this.role=role;}
 public Long getId(){return id;} public String getEmail(){return email;} public String getPasswordHash(){return passwordHash;}
 public String getFullName(){return fullName;} public Role getRole(){return role;} public Instant getCreatedAt(){return createdAt;}
 public void setFullName(String v){fullName=v;} public void setRole(Role v){role=v;}
}
