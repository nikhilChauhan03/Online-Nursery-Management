package com.masai.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Admin;

@Repository
public interface AdminRepositry extends JpaRepository<Admin, Integer>{

	public Admin findByUserName(String userName);
	
}
