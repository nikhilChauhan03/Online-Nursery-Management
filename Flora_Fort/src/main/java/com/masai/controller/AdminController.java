package com.masai.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.LoginDTO;
import com.masai.DTO.LogoutDTO;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@PostMapping("/admins")
	public ResponseEntity<Admin> registerAdminHandler(@Valid @RequestBody Admin admin) throws AdminException
	{
		return new ResponseEntity<Admin>(adminService.registerAsAdmin(admin),HttpStatus.CREATED);
	}
	
	@PostMapping("/adminslogin")
	public ResponseEntity<String> loginAdminHandler(@Valid @RequestBody LoginDTO login) throws AdminException
	{
		return new ResponseEntity<String>(adminService.login(login),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/admins")
	public ResponseEntity<Admin> updateAdminHandler(@Valid @RequestBody Admin admin) throws AdminException
	{
		return new ResponseEntity<Admin>(adminService.updateAdmin(admin),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/admins/{adminId}")
	public ResponseEntity<LogoutDTO> deleteAdminHandler(@PathVariable Integer adminId) throws AdminException
	{
		return new ResponseEntity<LogoutDTO>(adminService.deleteAdmin(adminId),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/admins/{admin_username}")
	public ResponseEntity<List<Customer>> viewAllCustomersHandler(@PathVariable String admin_username) throws AdminException, CustomerException
	{
		return new ResponseEntity<List<Customer>>(adminService.viewAllCustomers(admin_username),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/adminslogout/{admin_username}")
	public ResponseEntity<LogoutDTO> logOutAdminHandler(@PathVariable String admin_username) throws AdminException
	{
		return new ResponseEntity<LogoutDTO>(adminService.logOutAdmin(admin_username),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getcustomer/{customerId}/{admin_username}")
	public ResponseEntity<Customer> getCustomerByIdHandler(@PathVariable Integer customerId, @PathVariable String admin_username) throws AdminException, CustomerException
	{
		return new ResponseEntity<Customer>(adminService.getCustomerById(customerId,admin_username),HttpStatus.ACCEPTED);
	}
	
		
}
