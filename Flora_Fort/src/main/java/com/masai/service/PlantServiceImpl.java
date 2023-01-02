package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.PlantException;
import com.masai.model.Plant;
import com.masai.repositry.PlantRepository;


@Service
public class PlantServiceImpl implements PlantService {

//	IN EVERY METHOD WE NEED TO PROVIDE USERNAME TO AUTHENTICATE THE USER-------
	
	/* 	
	 * 	Plant repository : perfrom DML operations on the plant table.
	 * Admin repository : authenticate admin
	 * 	Customer repository : authenticate cutomer.
	 */
	
	
	@Autowired
	private PlantRepository plantRepository;
	
	@Autowired
	private AdminService adminSerivce;
	
	@Autowired
	private CustomerService customerService;
	
	
//	-------------------------------------registering plant into database-----------------------------------
	
	@Override
	public Plant registerPlant(Plant p,String user) throws AdminException,PlantException {
		
		if(!adminSerivce.validateAdmin(user))throw new AdminException("user is not valid or not logged in");
		
		return plantRepository.save(p);
		
	}

	
//	---------------------------------------------updating plant for that we need plant and user name ----------------------------------
	

	@Override
	public Plant updatePlantDetails(Plant plant, String user) throws PlantException, AdminException {
		
		if(!adminSerivce.validateAdmin(user))throw new AdminException("user is not valid or not logged in");
		
		Optional<Plant>opt=plantRepository.findById(plant.getPlantId());
		
		if(opt.isPresent())
		{
			Plant update=plantRepository.save(plant);
			return update;
		}
		else
			throw new PlantException("Invalid plant details");
		
	}

	
	
//	------------------------------------------------delete plant id by plant id and user name to authenticate---------------------------------

	@Override
	public Plant deletePlantById(Integer id, String str) throws PlantException, AdminException {
		
		if(!adminSerivce.validateAdmin(str))throw new AdminException("user is not valid or not logged in");
		
		Optional<Plant>opt=plantRepository.findById(id);
		
		if(opt.isPresent()) {
			
			Plant existing=opt.get();
			plantRepository.delete(existing);
			return existing;
			
		}
		else
			throw new PlantException("No such plant with id "+id);
		
		
	}

	
	
//----------------------------------------------getting plant by plant id and user name----------------------------------------------
	
	@Override
	public Plant getPlantById(Integer id,String str) throws PlantException,CustomerException, AdminException {
		
		if(!(adminSerivce.validateAdmin(str) || customerService.validateCustomer(str)))
            throw new AdminException("user is not valid...!");
		
		Optional<Plant>opt=plantRepository.findById(id);
		if(opt.isPresent()) {
			
			Plant plant=opt.get();
			return plantRepository.save(plant);
			
		}
		else
			throw new PlantException("Invalid Plant Id "+id);
		
	}

	
	
	
// -----------------------------------------------getting plant by common name of plant-----------------------------------------------
	
	@Override
	public Plant getPlantByCommonName(String cname,String str) throws PlantException,CustomerException, AdminException {
		
		 if(!(adminSerivce.validateAdmin(str) || customerService.validateCustomer(str)))
	            throw new AdminException("user is not valid...!");
		
		Plant plants=plantRepository.findByCommonName(cname);
		if(plants == null)
			throw new PlantException("No such plant with "+cname);

		return plants;
	}
	
	
	
}
