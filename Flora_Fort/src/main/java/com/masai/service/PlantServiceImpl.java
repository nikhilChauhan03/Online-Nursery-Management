package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.PlantException;
import com.masai.model.Plant;
import com.masai.repositry.PlantRepo;


@Service
public class PlantServiceImpl implements PlantService {

	@Autowired
	private PlantRepo pRepo;
	
	@Autowired
	private AdminService adminSerivce;
	
	@Autowired
	private CustomerService customerService;
	
	
	
	
	@Override
	public Plant registerPlant(Plant p,String user) throws AdminException,PlantException {
		
		if(!adminSerivce.validateAdmin(user))throw new AdminException("user is not valid or not logged in");
		
		return pRepo.save(p);
		
	}


	@Override
	public Plant updatePlantDetails(Plant plant, String user) throws PlantException, AdminException {
		
		if(!adminSerivce.validateAdmin(user))throw new AdminException("user is not valid or not logged in");
		
		Optional<Plant>opt=pRepo.findById(plant.getPlantId());
		if(opt.isPresent())
		{
			Plant update=pRepo.save(plant);
			return update;
		}
		else
			throw new PlantException("Invalid plant details");
		
	}


	@Override
	public Plant deletePlantById(Integer id, String str) throws PlantException, AdminException {
		
		if(!adminSerivce.validateAdmin(str))throw new AdminException("user is not valid or not logged in");
		Optional<Plant>opt=pRepo.findById(id);
		if(opt.isPresent()) {
			Plant existing=opt.get();
			pRepo.delete(existing);
			return existing;
		}
		else
			throw new PlantException("No such plant with id "+id);
		
		
	}


	@Override
	public Plant getPlantById(Integer id,String str) throws PlantException,CustomerException, AdminException {
		
		if(!(adminSerivce.validateAdmin(str) || customerService.validateCustomer(str)))
            throw new AdminException("user is not valid...!");
		
		Optional<Plant>opt=pRepo.findById(id);
		if(opt.isPresent()) {
			
			Plant plant=opt.get();
			return pRepo.save(plant);
			
		}
		else
			throw new PlantException("Invalid Plant Id "+id);
		
	}


	@Override
	public Plant getPlantByCommonName(String cname,String str) throws PlantException,CustomerException, AdminException {
		
		 if(!(adminSerivce.validateAdmin(str) || customerService.validateCustomer(str)))
	            throw new AdminException("user is not valid...!");
		
		Plant plants=pRepo.findByCommonName(cname);
		if(plants == null)
			throw new PlantException("No such plant with "+cname);

		return plants;
	}
}
