package com.college.resume.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="jobs")
public class Job {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=160) private String title;
 @Column(nullable=false,length=160) private String company;
 @Column(nullable=false,length=4000) private String description;
 @Column(nullable=false,length=2000) private String requiredSkills;
 @Column(length=120) private String location;
 @Column(nullable=false) private boolean active=true;
 @Column(nullable=false,updatable=false) private Instant createdAt=Instant.now();
 protected Job(){}
 public Job(String title,String company,String description,String requiredSkills,String location){this.title=title;this.company=company;this.description=description;this.requiredSkills=requiredSkills;this.location=location;this.active=true;}
 public Long getId(){return id;} public String getTitle(){return title;} public String getCompany(){return company;}
 public String getDescription(){return description;} public String getRequiredSkills(){return requiredSkills;}
 public String getLocation(){return location;} public boolean isActive(){return active;} public Instant getCreatedAt(){return createdAt;}
 public void setTitle(String v){title=v;} public void setCompany(String v){company=v;} public void setDescription(String v){description=v;}
 public void setRequiredSkills(String v){requiredSkills=v;} public void setLocation(String v){location=v;} public void setActive(boolean v){active=v;}
}
