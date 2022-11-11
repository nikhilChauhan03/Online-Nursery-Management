package com.masai.repositry;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Customer;

public interface CustomerRepositry extends JpaRepository<Customer, Integer>{
	
	public Customer findByUserName(String userName);
=======
public interface CustomerRepositry {
>>>>>>> 88c459ec88335a4cf198f16a412c1027339468a7

}
