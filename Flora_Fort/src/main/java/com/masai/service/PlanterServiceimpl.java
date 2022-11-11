package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.PlanterException;
import com.masai.model.Planter;
import com.masai.repositry.PlanterRepo;

@Service
public class PlanterServiceimpl  implements PlanterService{
	@Autowired
	private PlanterRepo planterdao; 
	
	@Override
	public Planter addPlanter(Planter planter) {
		
		Planter savedPlanter = planterdao.save(planter);
		
		return savedPlanter;
	}

	@Override
	public Planter updatePlanter(Planter planter) throws PlanterException {
		
		Optional<Planter> opt= planterdao.findById(planter.getPlanterId());
		
		if(opt.isPresent()) {
			
			return planterdao.save(planter);	
		}
		
		else {
			
			throw new PlanterException("Planter could not be Updated. Invalid Planter Details provided");
		}
		
	}

	@Override
	public Planter deletePlanter(Planter planter) throws PlanterException   {
		
		Optional<Planter> opt= planterdao.findById(planter.getPlanterId());
		
		if(opt.isPresent()) {
			
			Planter deletedPlanter = opt.get();
			
			planterdao.delete(deletedPlanter);
			
			return deletedPlanter;	
		}
		
		else {
			
			throw new PlanterException("Planter could not be Deleted. Invalid Planter Details ");
		}
		
		
	}

	@Override
	public Planter viewPlanter(Integer planterId) throws PlanterException {
		
		Optional<Planter> opt= planterdao.findById(planterId);
		
		return opt.orElseThrow(() -> new PlanterException("Planter does not exist with Roll :"+ planterId) );
	}
	
	
	@Override
	public Planter viewPlanter(String planterShape) throws PlanterException {
		
		Planter planterObj = planterdao.findByPlanterShape(planterShape);
		
		if(planterObj != null) {
			
			return planterObj; 
		}
		
		else {
			throw new PlanterException("Planter does not exist with planterShape :"+ planterShape);
		}
		
	}

	@Override
	public List<Planter> viewAllPlanters() throws PlanterException {
		
		List<Planter> planters = planterdao.findAll();
		
		if(planters.size() > 0) {
			return planters;
		}
		else {
			throw new PlanterException("No Planters Found");
		}
		
	}

	@Override
	public List<Planter> viewAllPlanters(Integer minCost, Integer maxCost) throws PlanterException {
		
		List<Planter> planters  = planterdao.findAllByPlanterCostRange(minCost, maxCost);
		
		if(planters.size() > 0) {
			return planters;
		}
		else {
			throw new PlanterException("No Planters Found At Provided Cost Range of Between " + minCost + " and " + maxCost);
		}
		
	}

	@Override
	public Integer viewcost(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
