package com.college.resume.controller;
import com.college.resume.model.ResumeAnalysis;
import com.college.resume.service.ResumeAnalyzerService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
@RestController @RequestMapping("/api/resumes")
public class ResumeController {
 private final ResumeAnalyzerService service;
 public ResumeController(ResumeAnalyzerService service){this.service=service;}
 @PostMapping(value="/analyze",consumes="multipart/form-data")
 public ResumeAnalysis analyze(Authentication auth,@RequestPart("file") MultipartFile file,@RequestParam(required=false) Long jobId)throws IOException{return service.analyze(auth.getName(),file,jobId);}
 @GetMapping("/history") public List<ResumeAnalysis> history(Authentication auth){return service.history(auth.getName());}
}
