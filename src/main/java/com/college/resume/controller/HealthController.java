package com.college.resume.controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController public class HealthController { @GetMapping("/api/health") public Map<String,String> health(){return Map.of("status","ok","application","AI Resume Analyzer and Placement System");} }
