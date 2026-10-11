package com.college.resume.service;
import com.college.resume.model.*;
import com.college.resume.repo.*;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
@Service
public class ResumeAnalyzerService {
 private static final List<String> SKILLS=List.of("java","python","javascript","typescript","react","spring boot","spring","sql","postgresql","mysql","mongodb","html","css","tailwind css","node.js","express","rest api","microservices","git","github","docker","kubernetes","aws","azure","machine learning","deep learning","natural language processing","nlp","pandas","numpy","scikit-learn","tensorflow","pytorch","data structures","algorithms","c","c++","communication","leadership","agile","linux","excel","power bi","tableau","hibernate","jpa","maven","testing","jest");
 private final JobRepository jobs;private final ResumeAnalysisRepository analyses;private final UserRepository users;
 public ResumeAnalyzerService(JobRepository jobs,ResumeAnalysisRepository analyses,UserRepository users){this.jobs=jobs;this.analyses=analyses;this.users=users;}
 public ResumeAnalysis analyze(String email,MultipartFile file,Long jobId)throws IOException{
  if(file.isEmpty())throw new IllegalArgumentException("Please select a PDF resume.");
  if(file.getSize()>5*1024*1024)throw new IllegalArgumentException("Resume must be 5 MB or smaller.");
  String name=Optional.ofNullable(file.getOriginalFilename()).orElse("resume.pdf");
  if(!name.toLowerCase().endsWith(".pdf")&&! "application/pdf".equalsIgnoreCase(file.getContentType()))throw new IllegalArgumentException("Only PDF resumes are supported.");
  String text;try(var pdf=Loader.loadPDF(file.getBytes())){text=new PDFTextStripper().getText(pdf);}
  if(text.isBlank())throw new IllegalArgumentException("No readable text found. Please upload a text-based PDF, not a scanned image.");
  String normalized=" "+text.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9+#.]+"," ")+" ";
  List<String> extracted=SKILLS.stream().filter(s->containsSkill(normalized,s)).distinct().toList();
  Job job=null;List<String> required=List.of();
  if(jobId!=null){job=jobs.findById(jobId).filter(Job::isActive).orElseThrow(()->new IllegalArgumentException("Selected job was not found or is inactive."));
   required=Arrays.stream(job.getRequiredSkills().split(",")).map(String::trim).filter(s->!s.isBlank()).map(String::toLowerCase).distinct().toList();}
  List<String> matched=required.stream().filter(s->extracted.contains(s)||containsSkill(normalized,s)).toList();
  List<String> missing=required.stream().filter(s->!matched.contains(s)).toList();
  int score=required.isEmpty()?Math.min(100,extracted.size()*5): (int)Math.round(matched.size()*100.0/required.size());
  List<String> tips=new ArrayList<>();
  if(extracted.isEmpty())tips.add("Add a clearly labelled technical-skills section using the skills you genuinely possess.");
  if(!missing.isEmpty())tips.add("Prioritize learning and demonstrating: "+String.join(", ",missing)+".");
  if(!text.toLowerCase().contains("project"))tips.add("Add 2–3 projects with your role, technologies, and measurable outcomes.");
  if(!text.toLowerCase().contains("education"))tips.add("Make your education and qualification details easy to find.");
  if(!text.toLowerCase().contains("intern"))tips.add("Include internships, practical training, or relevant hands-on experience if applicable.");
  if(tips.isEmpty())tips.add("Keep the resume concise, quantify project outcomes, and tailor keywords honestly to the selected role.");
  AppUser user=users.findByEmailIgnoreCase(email).orElseThrow();
  return analyses.save(new ResumeAnalysis(user,job,name,score,String.join(", ",extracted),String.join(", ",matched),String.join(", ",missing),String.join("\n",tips)));
 }
 private boolean containsSkill(String text,String skill){String s=skill.toLowerCase(Locale.ROOT);return Pattern.compile("(?<![a-z0-9])"+Pattern.quote(s)+"(?![a-z0-9])").matcher(text).find();}
 public List<ResumeAnalysis> history(String email){AppUser u=users.findByEmailIgnoreCase(email).orElseThrow();return analyses.findByUser_IdOrderByCreatedAtDesc(u.getId());}
}
