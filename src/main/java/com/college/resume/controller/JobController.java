package com.college.resume.controller;
import com.college.resume.model.Job;
import com.college.resume.repo.JobRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/jobs")
public class JobController {
 private final JobRepository jobs;
 public JobController(JobRepository jobs){this.jobs=jobs;}
 @GetMapping public List<Job> all(){return jobs.findByActiveTrueOrderByCreatedAtDesc();}
 @PostMapping public Job create(@Valid @RequestBody JobRequest r){return jobs.save(new Job(r.title(),r.company(),r.description(),r.requiredSkills(),r.location()));}
 @PutMapping("/{id}") public Job update(@PathVariable Long id,@Valid @RequestBody JobRequest r){Job j=jobs.findById(id).orElseThrow(()->new IllegalArgumentException("Job not found."));j.setTitle(r.title());j.setCompany(r.company());j.setDescription(r.description());j.setRequiredSkills(r.requiredSkills());j.setLocation(r.location());return jobs.save(j);}
 @DeleteMapping("/{id}") public void deactivate(@PathVariable Long id){Job j=jobs.findById(id).orElseThrow(()->new IllegalArgumentException("Job not found."));j.setActive(false);jobs.save(j);}
 public record JobRequest(@NotBlank @Size(max=160) String title,@NotBlank @Size(max=160) String company,@NotBlank @Size(max=4000) String description,@NotBlank @Size(max=2000) String requiredSkills,@Size(max=120) String location){}
}
