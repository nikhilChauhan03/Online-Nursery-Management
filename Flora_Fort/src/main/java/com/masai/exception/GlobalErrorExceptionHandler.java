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
	
	
//	@ExceptionHandler(SqlExceptionHelper.class)
//	public ResponseEntity<MyErrorDetails> myExceptionHandler(DataIntegrityViolationException ce, WebRequest rq)
//	{
//		
//		MyErrorDetails md = new MyErrorDetails(LocalDateTime.now(), , rq.getDescription(false));
//		
//		return new ResponseEntity<MyErrorDetails>(md,HttpStatus.BAD_REQUEST);
//		
//	}
//	
	
}
