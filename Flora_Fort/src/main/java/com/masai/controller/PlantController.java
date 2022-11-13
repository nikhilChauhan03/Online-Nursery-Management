package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.PlantException;
import com.masai.model.Plant;
import com.masai.service.PlantService;

@RestController
public class PlantController {

	@Autowired
	private PlantService pService;
	
	@PostMapping("/plants/{admin_userName}")
	public ResponseEntity<Plant> registerPlantHandler(@RequestBody Plant plant, @PathVariable String admin_userName) throws PlantException, AdminException{
		
		Plant savedPlant=pService.registerPlant(plant,admin_userName);
		return new ResponseEntity<Plant>(savedPlant,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/plants/{admin_userName}")
	public ResponseEntity<Plant>updatePlantsHandler(@RequestBody Plant plant,@PathVariable("admin_userName") String admin_userName)throws PlantException, AdminException{
		
		Plant updated=pService.updatePlantDetails(plant,admin_userName);
		
		return new ResponseEntity<Plant>(updated,HttpStatus.ACCEPTED);
		
		
	}
	@DeleteMapping("/plants/{plant_id}/{admin_userName}")
	public ResponseEntity<Plant>deletePlantByIdHandler(@PathVariable Integer plant_id, @PathVariable String admin_userName) throws PlantException, AdminException{
		
		Plant deleted=pService.deletePlantById(plant_id,admin_userName);
		return new ResponseEntity<Plant>(deleted,HttpStatus.OK);
	}
	
	@GetMapping("/plants/{plant_id}/{userName}")
	public ResponseEntity<Plant>getPlantByIdHandler(@PathVariable Integer plant_id, @PathVariable String userName)throws PlantException,CustomerException, AdminException{
		
		Plant plant=pService.getPlantById(plant_id,userName);
		return new ResponseEntity<Plant>(plant,HttpStatus.OK);
				
	}
	
	
	@GetMapping("/getplants/{plant_common_name}/{userName}")
	public ResponseEntity<Plant>getPlantByCommonNameHandler(@PathVariable String plant_common_name, @PathVariable String userName) throws PlantException, CustomerException, AdminException{
		
		Plant plants=pService.getPlantByCommonName(plant_common_name,userName);
		
		return new ResponseEntity<Plant>(plants,HttpStatus.OK);
		
	}
	
}
