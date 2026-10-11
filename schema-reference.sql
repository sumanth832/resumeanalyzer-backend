-- Reference schema only. Spring Data JPA creates/updates these tables automatically in development.
CREATE DATABASE resume_analyzer;
-- Connect to resume_analyzer before running application.
-- app_users: id, email (unique), password_hash, full_name, role, created_at
-- jobs: id, title, company, description, required_skills, location, active, created_at
-- resume_analyses: id, user_id FK -> app_users.id, job_id FK -> jobs.id nullable,
-- file_name, match_score, extracted_skills, matched_skills, missing_skills, recommendations, created_at
