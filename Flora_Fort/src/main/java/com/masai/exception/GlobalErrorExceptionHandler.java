package com.masai.exception;

import java.time.LocalDateTime;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;




@ControllerAdvice

public class GlobalErrorExceptionHandler {

//	------------------------------------ Customer Exception handler ---------------------------------------------------
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> customerExceptionHandler(CustomerException exception, WebRequest webRequest)
	{
		
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	
//	--------------------------------------------admin Exception handler -----------------------------------------------
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> adminExceptionHandler(AdminException exception, WebRequest webRequest)
	{
		
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
//	---------------------------------------------Validation Exception handler--------------------------------------------------
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidHandler(MethodArgumentNotValidException exception)
	{

		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(), "validation error", exception.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}
	
	
//	--------------------------------------------------global exception handler-----------------------------------------------
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception exception, WebRequest webRequest)
	{
		
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	
//	----------------------------------------------------------plant Exception handler----------------------------------------------------
	@ExceptionHandler(PlantException.class)
	public ResponseEntity<MyErrorDetails>plantExceptionHandler(PlantException exception, WebRequest webRequest){
		
		MyErrorDetails error=new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	
//		----------------------------------------------------------planter Exception handler----------------------------------------------------
	@ExceptionHandler(PlanterException.class)
	public ResponseEntity<MyErrorDetails> planterExceptionHandler(PlanterException exception,WebRequest webRequest){
		
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}
	
	
// ------------------------------------------------------------------Nohandlerfound Exception----------------------------------------------------
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> notFoundExceptionHandler(NoHandlerFoundException exception, WebRequest webRequest ){
		
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);

	}
	
//	---------------------------------------------------------------OrderException Handler--------------------------------------------------------------

	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> ordreExceptionHandler(OrderException exception, WebRequest webRequest ){
		
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);

	}
	
}
