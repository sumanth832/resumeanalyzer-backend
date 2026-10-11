package com.college.resume.repo;
import com.college.resume.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<AppUser,Long> { Optional<AppUser> findByEmailIgnoreCase(String email); boolean existsByEmailIgnoreCase(String email); }
