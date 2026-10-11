package com.college.resume.config;
import com.college.resume.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;
import java.util.List;
@Configuration
public class SecurityConfig {
 @Value("${app.cors.allowed-origin}") private String allowedOrigin;
 @Bean SecurityFilterChain filterChain(HttpSecurity http,JwtAuthFilter jwt)throws Exception{
  return http.csrf(c->c.disable()).cors(Customizer.withDefaults()).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authorizeHttpRequests(a->a.requestMatchers("/api/auth/**","/api/health","/error").permitAll()
    .requestMatchers(org.springframework.http.HttpMethod.GET,"/api/jobs").permitAll()
    .requestMatchers(org.springframework.http.HttpMethod.OPTIONS,"/**").permitAll()
    .requestMatchers(org.springframework.http.HttpMethod.POST,"/api/jobs").hasRole("PLACEMENT_OFFICER")
    .requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/jobs/**").hasRole("PLACEMENT_OFFICER")
    .requestMatchers(org.springframework.http.HttpMethod.DELETE,"/api/jobs/**").hasRole("PLACEMENT_OFFICER")
    .requestMatchers("/api/placement/**").hasRole("PLACEMENT_OFFICER")
    .anyRequest().authenticated())
   .addFilterBefore(jwt,UsernamePasswordAuthenticationFilter.class).build();
 }
 @Bean CorsConfigurationSource corsConfigurationSource(){
  CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(List.of(allowedOrigin));c.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));c.setAllowedHeaders(List.of("Authorization","Content-Type"));c.setAllowCredentials(false);
  UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource();s.registerCorsConfiguration("/**",c);return s;
 }
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
}
