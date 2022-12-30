package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.PlanterException;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seed;
import com.masai.repositry.PlantRepository;
import com.masai.repositry.PlanterRepository;
import com.masai.repositry.SeedRepository;

@Service
public class PlanterServiceimpl  implements PlanterService{
	 @Autowired
	    private PlanterRepository planterRepository; 
	    
	    
	    @Autowired
	    private PlantRepository plantRepository;
	    
	    @Autowired
	    private SeedRepository seedRepository;
	    
	    @Autowired
	    private AdminService adminSerivce;
	    @Autowired
	    private CustomerService customerService;
	    @Override
	    public Planter addPlanter(Planter planter,String user) throws AdminException {
	        
	        if(!adminSerivce.validateAdmin(user))throw new AdminException("user is not valid or not logged in");
	        
	        Planter savedPlanter = planterRepository.save(planter);
	        
	        return savedPlanter;
	    }
	    @Override
	    public Planter updatePlanter(Planter planter,String user) throws PlanterException,AdminException {
	        if(!adminSerivce.validateAdmin(user))throw new AdminException("user is not valid or not logged in");
	        Optional<Planter> opt= planterRepository.findById(planter.getPlanterId());
	        
	        if(opt.isPresent()) {
	            
	            return planterRepository.save(planter);    
	        }
	        
	        else {
	            
	            throw new PlanterException("Planter could not be Updated. Invalid Planter Details provided");
	        }
	        
	    }
	    @Override
	    public Planter deletePlanter(Integer planterId,String user) throws PlanterException,AdminException   {
	        if(!adminSerivce.validateAdmin(user))throw new AdminException("user is not valid or not logged in");
	        Optional<Planter> opt= planterRepository.findById(planterId);
	        
	        if(opt.isPresent()) {
	            
	            Planter deletedPlanter = opt.get();
	            
	            planterRepository.delete(deletedPlanter);
	            
	            return deletedPlanter;  
	        }
	        
	        else {
	            
	            throw new PlanterException("Planter could not be Deleted. Invalid Planter Details ");
	        }
	        
	        
	    }
	    @Override
	    public Planter viewPlanter(Integer planterId,String user) throws PlanterException,AdminException,CustomerException {
	    	if(!(customerService.validateCustomer(user) || adminSerivce.validateAdmin(user)))throw new CustomerException("user is not valid or not logged in");
	        Optional<Planter> opt= planterRepository.findById(planterId);
	        
	        return opt.orElseThrow(() -> new PlanterException("Planter does not exist with Roll :"+ planterId) );
	    }
	    
	    
	    @Override
	    public Planter viewPlanter(String planterShape,String user) throws PlanterException,AdminException,CustomerException {
	    	if(!(customerService.validateCustomer(user) || adminSerivce.validateAdmin(user)))throw new CustomerException("user is not valid or not logged in");
	        Planter planterObj = planterRepository.findByPlanterShape(planterShape);
	        
	        if(planterObj != null) {
	            
	            return planterObj; 
	        }
	        
	        else {
	            throw new PlanterException("Planter does not exist with planterShape :"+ planterShape);
	        }
	        
	    }
	    @Override
	    public List<Planter> viewAllPlanters(String user) throws PlanterException,AdminException,CustomerException {
	        if(!(customerService.validateCustomer(user) || adminSerivce.validateAdmin(user)))throw new CustomerException("user is not valid or not logged in");
	        List<Planter> planters = planterRepository.findAll();
	        
	        if(planters.size() > 0) {
	            return planters;
	        }
	        else {
	            throw new PlanterException("No Planters Found");
	        }
	        
	    }
	    @Override
	    public List<Planter> viewAllPlanters(Integer minCost, Integer maxCost,String user) throws PlanterException,AdminException,CustomerException {
	    	 if(!(customerService.validateCustomer(user) || adminSerivce.validateAdmin(user)))throw new CustomerException("user is not valid or not logged in");
	        List<Planter> planters  = planterRepository.findAllByPlanterCostRange(minCost, maxCost);
	        
	        if(planters.size() > 0) {
	            return planters;
	        }
	        else {
	            throw new PlanterException("No Planters Found At Provided Cost Range of Between " + minCost + " and " + maxCost);
	        }
	        
	    }
	//  --------------------------------register planter with plant and seed ------------------------------------
	    
	    @Override
	    public Planter registerPlanter(Integer planterId,Integer plantId, Integer seedId,String user) throws PlanterException,AdminException{
	        if(!adminSerivce.validateAdmin(user))throw new AdminException("user is not valid or not logged in");
	        Plant plant = plantRepository.findById(plantId).orElseThrow(() -> new PlanterException("Invalid plnatId"));
	        
	        Seed seed = seedRepository.findById(seedId).orElseThrow(() -> new PlanterException("Invalid seedId"));
	        
	        Planter planter = planterRepository.findById(planterId).orElseThrow(() -> new PlanterException("Invalid plnaterId"));
	        
	        planter.getPlants().add(plant);
	        plant.setPlanter(planter);
	        
	        
	        planter.getSeeds().add(seed);
	        seed.setPlanter(planter);
	    
	        return planterRepository.save(planter);
	        
	        
	        
	    }

}
