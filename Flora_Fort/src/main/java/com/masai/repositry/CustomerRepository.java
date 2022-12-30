package com.masai.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Customer findByUserName(String userName);

}
