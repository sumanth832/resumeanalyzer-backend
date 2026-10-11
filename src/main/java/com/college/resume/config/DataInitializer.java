package com.college.resume.config;
import com.college.resume.model.Job;
import com.college.resume.repo.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DataInitializer {
 @Bean CommandLineRunner seedJobs(JobRepository jobs){return args->{if(jobs.count()==0){
  jobs.save(new Job("Java Backend Developer","Sample Technology Company","Build REST APIs, work with databases, and maintain Java services.","java,spring boot,sql,postgresql,rest api,git,maven,hibernate","Hyderabad / Hybrid"));
  jobs.save(new Job("Frontend React Developer","Sample Product Studio","Build accessible responsive interfaces using React and modern JavaScript.","javascript,react,html,css,rest api,git,testing","Hyderabad / Remote"));
  jobs.save(new Job("Junior Data Analyst","Sample Analytics Group","Prepare data, analyze trends, and communicate findings to teams.","python,sql,pandas,numpy,excel,power bi,communication","India / Hybrid"));
  jobs.save(new Job("Machine Learning Intern","Sample AI Labs","Prototype and evaluate ML models for practical problems.","python,machine learning,natural language processing,pandas,numpy,scikit-learn,git","Hyderabad / Hybrid"));
 }};}
}
