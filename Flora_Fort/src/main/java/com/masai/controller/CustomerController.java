package com.masai.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.LoginDTO;
import com.masai.DTO.LogoutDTO;

import com.masai.exception.CustomerException;
import com.masai.exception.PlantException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Plant;
import com.masai.repositry.PlantRepo;
import com.masai.service.CustomerService;
import com.masai.service.PlantService;



@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	
	
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomerHandler(@Valid @RequestBody Customer cos) throws CustomerException
	{
		Customer customer = customerService.addCustomer(cos);
		return new ResponseEntity<Customer>(customer,HttpStatus.CREATED);
	}
	
	@PostMapping("/customerslogin")
	public ResponseEntity<String> loginCustomerHandler(@Valid @RequestBody LoginDTO login) throws CustomerException
	{
		return new ResponseEntity<String>(customerService.validateCustomer(login),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/customerslogout/{userName}")
	public ResponseEntity<LogoutDTO> logOutCustomerHandler(@PathVariable String userName) throws CustomerException
	{
		return new ResponseEntity<LogoutDTO>(customerService.logOutCustomer(userName),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException
	{
		return new ResponseEntity<Customer>(customerService.updateCustomer(customer),HttpStatus.OK);
	}
	
	@DeleteMapping("/customers/{userId}")
	public ResponseEntity<LogoutDTO> logOutCustomerHandler(@PathVariable Integer userId) throws CustomerException
	{
		return new ResponseEntity<LogoutDTO>(customerService.deleteCustomer(userId),HttpStatus.ACCEPTED);
	}
	
	
	
//	=================================SUBOJIT Plant=================================
	
	@Autowired
	private PlantService pService;
	@GetMapping("/plants/{id}/{user}")
	public ResponseEntity<Plant>getPlantByIdHandler(@PathVariable("id") Integer id, @PathVariable("user") String user)throws PlantException,CustomerException{
		
		Plant plant=pService.getPlantById(id,user);
		return new ResponseEntity<Plant>(plant,HttpStatus.OK);
			
		
		
	}
	@GetMapping("/getplants/{cname}/{user}")
	public ResponseEntity<List<Plant>>getPlantByCommonNameHandler(@PathVariable("cname") String cname, @PathVariable("user")String user) throws PlantException, CustomerException{
		
		List<Plant>plants=pService.getPlantByCommonName(cname,user);
		
		return new ResponseEntity<List<Plant>>(plants,HttpStatus.OK);
		
	}
	
	
}
