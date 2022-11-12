package com.masai.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.DTO.LoginDTO;
import com.masai.DTO.LogoutDTO;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.model.Admin;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.repositry.AdminRepositry;
import com.masai.repositry.CurrentUserSessionRepositry;
import com.masai.repositry.CustomerRepositry;

import lombok.val;
import net.bytebuddy.utility.RandomString;

@Service
public class AdminServiceImpl implements AdminService{

// 	----------- admin Repository Variable-----------
	@Autowired
	AdminRepositry adminRepositry;
	
//	----------- CurrentSessionRepository variable-----------
	@Autowired
	CurrentUserSessionRepositry currentUserSessionRepo;
	
// 	----------- Customer Repository Variable-----------
	@Autowired
	CustomerRepositry customerRepositry;
	
	
	
//	---------------------------------------register admin-----------------------------------------------
	
	
	@Override
	public Admin registerAsAdmin(Admin admin) throws AdminException {
		if(admin.getAdminId() != null) throw new AdminException("Admin Id is not required");
		Admin addAdmin = adminRepositry.save(admin);
		if(addAdmin == null) throw new AdminException("Failed to add admin to the data base");
		return addAdmin;
	}

	
//	-----------------------------------------log in admin-----------------------------------------------
	
	@Override
	public String login(LoginDTO user) throws AdminException {
		
//		step 1: check the type of user
		if(user.getTypeOfUser().equalsIgnoreCase("admin"))
		{
//		step 2: check that this username is present inside the customer table or not
			Admin existingAdmin = adminRepositry.findByUserName(user.getUserName());
			
			if(existingAdmin == null) throw new AdminException("Please Provide the valid details. Invalid UserName");
//		step 3: check that this existing customer username is already present inside the current session table or not, it will make
//			    sure that customer is already logged in or not.
			CurrentUserSession validCustomer = currentUserSessionRepo.findByUserName(existingAdmin.getUserName());
			if(validCustomer != null) throw new AdminException("this user is already logged in");
			
//		step4: check that password is correct or not
			if(existingAdmin.getPassword().equals(user.getPassword()))
			{
				
				String key = RandomString.make(6);
				
				CurrentUserSession logInCustomer = new CurrentUserSession(existingAdmin.getUserName(), key, LocalDateTime.now(), "admin");
				
				System.out.println(logInCustomer);
				
				currentUserSessionRepo.save(logInCustomer);
				
				return "Admin login successfull. your user key is " + key + " . use this key to send or get request to the server";
				
			}
			
			throw new AdminException("Please Provide the valid details. Invalid password");
			
			
		}
		throw new AdminException("Please Provide the valid details. Invalid user type");
		
	}

	
//	------------------------------------------update admin-----------------------------------------------
	
	
	@Override
	public Admin updateAdmin(Admin admin) throws AdminException {
		
		Admin existingAdmin = adminRepositry.findById(admin.getAdminId())
				.orElseThrow(() -> new AdminException("Invalid admin details. Please provide the valid details. admin Id is not vlaid"));

		CurrentUserSession checkUser = currentUserSessionRepo.findByUserName(admin.getUserName());
		
		if(checkUser == null) throw new AdminException("Please Provide valid details Invalid admin userName or First try to Login");
		
		if(!checkUser.getTypeOfUser().equalsIgnoreCase("admin")) throw new AdminException("Plese check your details. Customer is already login with these details.");
		
		return adminRepositry.save(admin);
	}

	
//	---------------------------------------------delete admin--------------------------------------------
	
	
	@Override
	public LogoutDTO deleteAdmin(Integer AdminId) throws AdminException {
		
		Admin admin = adminRepositry.findById(AdminId)
					  .orElseThrow(()-> new AdminException("Please provide valid AdminId"));
		
		CurrentUserSession checkUser = currentUserSessionRepo.findByUserName(admin.getUserName());
		if(checkUser == null) throw new  AdminException("Your are not Login");
		if(!checkUser.getTypeOfUser().equalsIgnoreCase("admin")) throw new AdminException("current user is not Admin. you need to login with admin account to perfom this operation");
		
		adminRepositry.delete(admin);
		currentUserSessionRepo.delete(checkUser);
		
		LogoutDTO response = new LogoutDTO(checkUser, "Admin deltails deleted successfully. Amin logout...");
		return response;
		
	
	}

	
//	------------------------------------------view all customers------------------------------------------
	
	@Override
	public List<Customer> viewAllCustomers(String userName) throws CustomerException,AdminException {
		
		List<Customer> listOfCustomers = new ArrayList<>();
		
		Admin admin = adminRepositry.findByUserName(userName);
		if(admin == null) throw new AdminException("Invalid admin userName, first register as admin");
		
		CurrentUserSession checkAdmin = currentUserSessionRepo.findByUserName(admin.getUserName());
		if(checkAdmin == null) throw new AdminException("Invalid admin userName");
		if(!checkAdmin.getTypeOfUser().equalsIgnoreCase("admin")) throw new AdminException("Only admin is allow to perform this operation");
		
		listOfCustomers = customerRepositry.findAll();
		
		if(listOfCustomers.isEmpty()) throw new CustomerException("No customer is present in database");
		
		return listOfCustomers;

	}

	
//	-----------------------------------------log out admin------------------------------------------------	

	@Override
	public LogoutDTO logOutAdmin(String user) throws AdminException {
		
		CurrentUserSession existingAdmin = currentUserSessionRepo.findByUserName(user);
		
		if(existingAdmin == null) throw new AdminException("Please provide the valid detials. Invalid UserName");
		
		currentUserSessionRepo.delete(existingAdmin);
		
		LogoutDTO response = new LogoutDTO(existingAdmin,"admin logout successfull");
	
		return response;
	}


//	-------------------------------------------validate admin----------------------------------------------
	
	
	@Override
	public Boolean validateAdmin(String user) throws AdminException {
		Admin admin = adminRepositry.findByUserName(user);
		if(admin == null) return false;
		CurrentUserSession checkUser = currentUserSessionRepo.findByUserName(user);
		if(checkUser == null) return false;
		if(!checkUser.getTypeOfUser().equalsIgnoreCase("admin")) return false;
		return true;
		
	}


//	--------------------------------------------get customer by customerId---------------------------------------------
	@Override
	public Customer getCustomerById(Integer customerId, String user) throws CustomerException,AdminException {
		
		validateAdmin(user);
		Customer customer = customerRepositry.findById(customerId).orElseThrow(()-> new CustomerException("Invalid customer Id"));
		return customer;
	}

}
