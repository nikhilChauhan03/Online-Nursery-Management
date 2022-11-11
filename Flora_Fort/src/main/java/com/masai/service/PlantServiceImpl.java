package com.masai.service;

import java.util.List;
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
	private CustomerService customerService;
	
	
	
	
	@Override
	public Plant registerPlant(Plant p, String users) throws AdminException,PlantException {
		
		adminSerivce.validateAdmin(users);
		
		Plant savedPlant=pRepo.save(p);
		return savedPlant;
		
	}


	@Override
	public Plant updatePlantDetails(Plant plant, String user) throws PlantException, AdminException {
		
		adminSerivce.validateAdmin(user);
		
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
		
		adminSerivce.validateAdmin(str);
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
	public Plant getPlantById(Integer id,String str) throws PlantException,CustomerException {
		
		customerService.validateCustomer(str);
		
		Optional<Plant>opt=pRepo.findById(id);
		if(opt.isPresent()) {
			
			Plant plant=opt.get();
			return plant;
			
		}
		else
			throw new PlantException("Invalid Plant Id "+id);
		
	}


	@Override
	public List<Plant> getPlantByCommonName(String cname,String str) throws PlantException,CustomerException {
		
		customerService.validateCustomer(str);
		
		List<Plant>plants=pRepo.findBycommonName(cname);
		if(plants.size()==0)
			throw new PlantException("No such plant with "+cname);
		else
			return plants;
	}
}
