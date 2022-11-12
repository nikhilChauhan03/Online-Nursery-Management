package com.masai.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.PlanterException;
import com.masai.model.Planter;
import com.masai.service.PlanterService;

@RestController
public class PlanterController {
	
	@Autowired
	private PlanterService planterService;
	
	
		@GetMapping("/planters/{id}")               
		public ResponseEntity<Planter> viewPlanterHandler(@Valid @PathVariable("id") int planterId) throws PlanterException {
			
			Planter obtainedPlanter = planterService.viewPlanter(planterId);
					
			return new ResponseEntity<Planter>(obtainedPlanter, HttpStatus.OK);
		}
		
		
		@GetMapping("/plnatershape/{shape}")              
		public ResponseEntity<Planter> viewPlanterByShape(@Valid @PathVariable("shape") String planterShape) throws PlanterException {
			
			Planter planter = planterService.viewPlanter(planterShape);
					
			return new ResponseEntity<Planter>(planter, HttpStatus.OK);
		}
		
		
		@GetMapping("/planters")              
		public ResponseEntity<List<Planter>> findAllPlanters() throws PlanterException {
			
			List<Planter> planterList = planterService.viewAllPlanters();
					
			return new ResponseEntity<List<Planter>>(planterList, HttpStatus.OK);
		}
		

		@GetMapping("/planters/{minCost}/{maxCost}")   
		public ResponseEntity<List<Planter>> viewPlantersByCostRange(@Valid @PathVariable("minCost") Integer minCost, @PathVariable("maxCost") Integer maxCost) throws PlanterException {
			
			List<Planter> planterList = planterService.viewAllPlanters(minCost, maxCost);
			
			return new ResponseEntity<List<Planter>>(planterList, HttpStatus.OK);
		}
		
		
		@PostMapping("/plnaters")
		public ResponseEntity<Planter> regiserPlanterWithSeedAndPlantHandler(@RequestParam Integer planter, @RequestParam Integer plantId, @RequestParam Integer seedId) throws PlanterException
		{
			return new ResponseEntity<>(planterService.registerPlanter(planter, plantId, seedId),HttpStatus.CREATED);
		}
		

}