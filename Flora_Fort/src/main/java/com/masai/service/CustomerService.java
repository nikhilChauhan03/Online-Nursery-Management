package com.masai.service;


import com.masai.DTO.LoginDTO;
import com.masai.DTO.LogoutDTO;
import com.masai.exception.CustomerException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;

public interface CustomerService {

//	---------------------------adding customer--------------------------------
	public Customer addCustomer(Customer customer) throws CustomerException;
	
//	------------------------updating customer-----------------------------------
	public Customer updateCustomer(Customer tenant) throws CustomerException;
	
//	--------------------------delete customer---------------------------------
	public LogoutDTO deleteCustomer(Integer customerId) throws CustomerException;
	
//	----------------------------login customer------------------------------
	public String validateCustomer(LoginDTO user) throws CustomerException;
	
//	----------------------------log out customer-------------------------------
	public LogoutDTO logOutCustomer(String user) throws CustomerException;
	
//	-----------------------------validate customer-----------------------------
	public Boolean validateCustomer(String user) throws CustomerException;
	
	
}
