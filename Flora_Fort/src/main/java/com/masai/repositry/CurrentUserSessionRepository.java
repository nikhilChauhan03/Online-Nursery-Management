package com.masai.repositry;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.CurrentUserSession;
@Repository
public interface CurrentUserSessionRepository extends JpaRepository<CurrentUserSession, String>{

	public CurrentUserSession findByUserName(String string);
	
}
