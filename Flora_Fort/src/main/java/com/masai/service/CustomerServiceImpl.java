package com.masai.service;


import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.DTO.LoginDTO;
import com.masai.DTO.LogoutDTO;
import com.masai.exception.CustomerException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.repositry.CurrentUserSessionRepositry;
import com.masai.repositry.CustomerRepositry;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerServiceImpl implements CustomerService{

// 	----------- Customer Repository Variable-----------
	@Autowired
	CustomerRepositry customerRepositry;
	
//	----------- CurrentSessionRepository variable-----------
	@Autowired
	CurrentUserSessionRepositry currentUserSessionRepo;

	
//	-------------------------------------- adding customer--------------------------------------------------
	
	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		if(customer.getCustomerId() != null) throw new CustomerException("customer Id is not required");
		Customer addcustomer = customerRepositry.save(customer);
		if(addcustomer == null) throw new CustomerException("Failed to Register Customer");
		System.out.print(customer.toString());
		return addcustomer;
		
	}
	
	
//	--------------------------------------updating customer-------------------------------------------------

	@Override
	public Customer updateCustomer(Customer tenant) throws CustomerException {

		Customer existingCustomer = customerRepositry.findById(tenant.getCustomerId())
									.orElseThrow(() -> new CustomerException("Invalid customer details. Please provide the valid details. Customer Id is not vlaid"));
		
		CurrentUserSession checkCustomer = currentUserSessionRepo.findByUserName(existingCustomer.getUserName());
		System.out.println(checkCustomer);
		if(checkCustomer == null) throw new CustomerException("Please Provide valid details Invalid Customer userName or First try to Login");

		return customerRepositry.save(tenant);
	
	}
	
	
//	---------------------------------------delete customer--------------------------------------------------

	@Override
	public LogoutDTO deleteCustomer(Integer customerId) throws CustomerException {
		
			Customer existingCustomer = customerRepositry.findById(customerId)
										.orElseThrow(() -> new CustomerException("Please check the customer id. Invalid Customer Id."));
			
			CurrentUserSession checkCustomer = currentUserSessionRepo.findByUserName(existingCustomer.getUserName());
			
			if(checkCustomer == null) throw new CustomerException("Failed to Delete the Customer. Current user is not login");
			
			customerRepositry.delete(existingCustomer);
			currentUserSessionRepo.delete(checkCustomer);
			
			LogoutDTO response = new LogoutDTO(checkCustomer,"Customer is deleted successfully. Customer is LogOut.....");
			return response;
			
	}

	
//	---------------------------------------------login Customer-------------------------------------------
	
	@Override
	public String validateCustomer(LoginDTO user) throws CustomerException {
//		step 1: check the type of user
		if(user.getTypeOfUser().equalsIgnoreCase("customer"))
		{
//		step 2: check that this username is present inside the customer table or not
			Customer existingCustomer = customerRepositry.findByUserName(user.getUserName());
			
			if(existingCustomer == null) throw new CustomerException("Please Provide the valid details. Invalid UserName");
//		step 3: check that this existing customer username is already present inside the current session table or not, it will make
//			    sure that customer is already logged in or not.
			CurrentUserSession validCustomer = currentUserSessionRepo.findByUserName(existingCustomer.getUserName());
			if(validCustomer != null) throw new CustomerException("this user is already logged in");
			
//		step4: check that password is correct or not
			if(existingCustomer.getUserPassword().equals(user.getPassword()))
			{
				
				String key = RandomString.make(6);
				
				CurrentUserSession logInCustomer = new CurrentUserSession(existingCustomer.getUserName(), key, LocalDateTime.now(), "customer");
				
				System.out.println(logInCustomer);
				
				currentUserSessionRepo.save(logInCustomer);
				
				return "Customer login successfull. you user key is " + key + " . use this key to send or get request to the server";
				
			}
			
			throw new CustomerException("Please Provide the valid details. Invalid password");
			
			
		}
		throw new CustomerException("Please Provide the valid details. Invalid user type");
	}

	
	
//	---------------------------------------- logout Customer--------------------------------------------------

	@Override
	public LogoutDTO logOutCustomer(String user) throws CustomerException {
		
			CurrentUserSession existingCustomer = currentUserSessionRepo.findByUserName(user);
			
			if(existingCustomer == null) throw new CustomerException("Please provide the valid detials. Invalid UserName");
			
			currentUserSessionRepo.delete(existingCustomer);
			
			LogoutDTO response = new LogoutDTO(existingCustomer," customer logOut successfull");
			
			return response;
			
	}

	
//	-------------------------------------------- validate Customer---------------------------------------------

	@Override
	public Boolean validateCustomer(String user) throws CustomerException{
		
		Customer customer = customerRepositry.findByUserName(user);
		if(customer == null) return false;
		
		CurrentUserSession checkCustomer = currentUserSessionRepo.findByUserName(user);
		if(checkCustomer == null) return false;
		if(!checkCustomer.getTypeOfUser().equalsIgnoreCase("customer")) return false;
		
		return true;
		
	}
	
	
	

}
