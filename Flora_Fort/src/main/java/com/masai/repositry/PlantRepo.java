package com.masai.repositry;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Plant;

@Repository
public interface PlantRepo extends JpaRepository<Plant, Integer>{
	
	
	public  Plant findByCommonName(String cname);
	//public List<Plant> findBytypeOfPlant(String type);

}
