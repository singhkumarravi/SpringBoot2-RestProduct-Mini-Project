
  package com.olive.exception.handler;
  
  import org.springframework.http.HttpStatus; import
  org.springframework.http.ResponseEntity; import
  org.springframework.web.bind.annotation.ExceptionHandler; import
  org.springframework.web.bind.annotation.RestControllerAdvice;
  
  import com.olive.exception.ProductNotFoundException;
  
  @RestControllerAdvice
  public class CustomExceptionHandler {
  
  @ExceptionHandler(ProductNotFoundException.class)
  public  ResponseEntity<String> prodHandle(ProductNotFoundException p){
	  return new ResponseEntity<String>(p.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
	  } 
  
  }
 