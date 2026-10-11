package com.college.resume.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<Map<String,String>> badRequest(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()==null?"Invalid request":e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,String>> validation(MethodArgumentNotValidException e){String msg=e.getBindingResult().getFieldErrors().stream().findFirst().map(x->x.getField()+": "+x.getDefaultMessage()).orElse("Invalid request");return ResponseEntity.badRequest().body(Map.of("message",msg));}
 @ExceptionHandler(HttpMessageNotReadableException.class) ResponseEntity<Map<String,String>> malformed(){return ResponseEntity.badRequest().body(Map.of("message","Request body is invalid."));}
 @ExceptionHandler(Exception.class) ResponseEntity<Map<String,String>> server(Exception e){return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Unexpected server error. Check backend logs."));}
}
