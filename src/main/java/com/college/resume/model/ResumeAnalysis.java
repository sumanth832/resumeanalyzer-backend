package com.college.resume.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="resume_analyses")
public class ResumeAnalysis {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="user_id") private AppUser user;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="job_id") private Job job;
 @Column(nullable=false,length=220) private String fileName;
 @Column(nullable=false) private int matchScore;
 @Column(nullable=false,length=5000) private String extractedSkills;
 @Column(nullable=false,length=5000) private String matchedSkills;
 @Column(nullable=false,length=5000) private String missingSkills;
 @Column(nullable=false,length=5000) private String recommendations;
 @Column(nullable=false,updatable=false) private Instant createdAt=Instant.now();
 protected ResumeAnalysis(){}
 public ResumeAnalysis(AppUser user,Job job,String fileName,int score,String extracted,String matched,String missing,String recommendations){
 this.user=user;this.job=job;this.fileName=fileName;this.matchScore=score;this.extractedSkills=extracted;this.matchedSkills=matched;this.missingSkills=missing;this.recommendations=recommendations;
 }
 public Long getId(){return id;} public Long getUserId(){return user.getId();} public String getUserName(){return user.getFullName();}
 public Long getJobId(){return job==null?null:job.getId();} public String getJobTitle(){return job==null?"General resume review":job.getTitle();}
 public String getCompany(){return job==null?"":job.getCompany();} public String getFileName(){return fileName;} public int getMatchScore(){return matchScore;}
 public String getExtractedSkills(){return extractedSkills;} public String getMatchedSkills(){return matchedSkills;} public String getMissingSkills(){return missingSkills;}
 public String getRecommendations(){return recommendations;} public Instant getCreatedAt(){return createdAt;}
}
