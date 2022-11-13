package com.masai.service;



import java.util.List;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.PlantException;
import com.masai.model.Plant;

public interface PlantService {
	
	public Plant registerPlant(Plant plant, String user)throws PlantException, AdminException;
	
	
	
	public Plant updatePlantDetails(Plant plant,String str) throws PlantException, AdminException;
	
	public Plant deletePlantById(Integer id,String str) throws PlantException, AdminException;
	
	public Plant getPlantById(Integer id,String str)throws PlantException, CustomerException,AdminException;
	
	public Plant getPlantByCommonName(String cname,String str) throws PlantException, CustomerException ,AdminException;
	
	

}
