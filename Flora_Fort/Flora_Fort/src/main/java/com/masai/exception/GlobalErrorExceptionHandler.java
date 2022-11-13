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
	public ResponseEntity<MyErrorDetails> CustomerExceptionHandler(CustomerException ce, WebRequest rq)
	{
		
		MyErrorDetails md = new MyErrorDetails(LocalDateTime.now(), ce.getMessage(), rq.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(md,HttpStatus.BAD_REQUEST);
		
	}
	
	
//	--------------------------------------------admin Exception handler -----------------------------------------------
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> AdminExceptionHandler(AdminException ce, WebRequest rq)
	{
		
		MyErrorDetails md = new MyErrorDetails(LocalDateTime.now(), ce.getMessage(), rq.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(md,HttpStatus.BAD_REQUEST);
		
	}
	
//	---------------------------------------------Validation Exception handler--------------------------------------------------
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidHandler(MethodArgumentNotValidException ce)
	{

		MyErrorDetails md = new MyErrorDetails(LocalDateTime.now(), "validation error", ce.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(md,HttpStatus.BAD_REQUEST);
	}
	
	
//	--------------------------------------------------global exception handler-----------------------------------------------
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception ce, WebRequest rq)
	{
		
		MyErrorDetails md = new MyErrorDetails(LocalDateTime.now(), ce.getMessage(), rq.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(md,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(PlantException.class)
	public ResponseEntity<MyErrorDetails>plantExceptionHandler(PlantException ce, WebRequest req){
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), ce.getMessage(), req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(PlanterException.class)
	public ResponseEntity<MyErrorDetails> plantExceptionHandler(PlanterException se,WebRequest req){
		
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),se.getMessage(),req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> notFoundExceptionHandler(NoHandlerFoundException nfe, WebRequest req ){
		
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),nfe.getMessage(),req.getDescription(false));
		
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	

	
	
}
