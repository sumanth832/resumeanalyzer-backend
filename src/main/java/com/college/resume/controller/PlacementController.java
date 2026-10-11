package com.college.resume.controller;
import com.college.resume.model.*;
import com.college.resume.repo.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/placement")
public class PlacementController {
 private final ResumeAnalysisRepository analyses;private final UserRepository users;private final JobRepository jobs;
 public PlacementController(ResumeAnalysisRepository a,UserRepository u,JobRepository j){analyses=a;users=u;jobs=j;}
 @GetMapping("/dashboard") public Map<String,Object> dashboard(){
  List<ResumeAnalysis> all=analyses.findAllByOrderByCreatedAtDesc();
  return Map.of("students",users.count(),"jobs",jobs.findByActiveTrueOrderByCreatedAtDesc().size(),"analyses",all.size(),"averageMatchScore",all.isEmpty()?0:(int)Math.round(all.stream().mapToInt(ResumeAnalysis::getMatchScore).average().orElse(0)),"recentAnalyses",all.stream().limit(20).toList());
 }
}
