# AI Resume Analyzer and Placement System — Backend

Java 17 + Spring Boot 3 + PostgreSQL REST API. PDF resume text extraction uses Apache PDFBox. Skill matching is a transparent keyword-based baseline, not a trained AI model; results are decision support, not hiring decisions.

## Requirements
- JDK 17+
- Maven 3.9+ (or use an IDE's Maven support)
- PostgreSQL 14+

## Database
Create a database named `resume_analyzer` in PostgreSQL:
```sql
CREATE DATABASE resume_analyzer;
```
Set `DB_USERNAME` and `DB_PASSWORD` environment variables, or edit `src/main/resources/application.properties` for local-only development. Hibernate creates/updates the tables `app_users`, `jobs`, and `resume_analyses` on startup. For a production deployment, use a managed PostgreSQL database and set environment variables; do not commit passwords or production secrets.

## Run
```bash
mvn spring-boot:run
```
API health check: `GET http://localhost:8080/api/health`.

## Main endpoints
- `POST /api/auth/register` JSON `{ "fullName":"Student Name", "email":"student@example.com", "password":"atleast8chars" }`
- `POST /api/auth/login` JSON `{ "email":"student@example.com", "password":"atleast8chars" }`
- `GET /api/auth/me` (Bearer token)
- `GET /api/jobs` public seeded job roles
- `POST /api/resumes/analyze` multipart fields `file` (PDF) and optional `jobId` (Bearer token)
- `GET /api/resumes/history` (Bearer token)
- `POST /api/jobs`, `PUT /api/jobs/{id}`, `DELETE /api/jobs/{id}` (PLACEMENT_OFFICER role)
- `GET /api/placement/dashboard` (PLACEMENT_OFFICER role)

## Placement officer
For a local demonstration only, register a normal account and promote it in PostgreSQL:
```sql
UPDATE app_users SET role='PLACEMENT_OFFICER' WHERE email='officer@example.com';
```
Log out and log in again. Never expose database credentials or a privileged registration path publicly.

## Notes
- Only text-based PDF files are supported; scanned image PDFs need OCR, which is not included.
- JWT secret must be at least 32 bytes.
- CORS origin defaults to `http://localhost:5173`; set `FRONTEND_URL` to the deployed frontend origin.
