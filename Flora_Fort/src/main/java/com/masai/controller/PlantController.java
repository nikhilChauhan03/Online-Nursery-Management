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
	
	@PostMapping("/plants/{user}")
	public ResponseEntity<Plant> registerPlantHandler(@RequestBody Plant plant, @PathVariable String user) throws PlantException, AdminException{
		
		Plant savedPlant=pService.registerPlant(plant,user);
		return new ResponseEntity<Plant>(savedPlant,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/plants/{user}")
	public ResponseEntity<Plant>updatePlantsHandler(@RequestBody Plant plant,@PathVariable("user") String user)throws PlantException, AdminException{
		
		Plant updated=pService.updatePlantDetails(plant,user);
		
		return new ResponseEntity<Plant>(updated,HttpStatus.ACCEPTED);
		
		
	}
	@DeleteMapping("/plants/{id}/{user}")
	public ResponseEntity<Plant>deletePlantByIdHandler(@PathVariable("id") Integer id, @PathVariable("user")String user) throws PlantException, AdminException{
		
		Plant deleted=pService.deletePlantById(id,user);
		return new ResponseEntity<Plant>(deleted,HttpStatus.OK);
	}
	
	@GetMapping("/plants/{id}/{user}")
	public ResponseEntity<Plant>getPlantByIdHandler(@PathVariable("id") Integer id, @PathVariable("user") String user)throws PlantException,CustomerException{
		
		Plant plant=pService.getPlantById(id,user);
		return new ResponseEntity<Plant>(plant,HttpStatus.OK);
			
		
		
	}
	@GetMapping("/getplants/{cname}/{user}")
	public ResponseEntity<Plant>getPlantByCommonNameHandler(@PathVariable("cname") String cname, @PathVariable("user")String user) throws PlantException, CustomerException{
		
		Plant plants=pService.getPlantByCommonName(cname,user);
		
		return new ResponseEntity<Plant>(plants,HttpStatus.OK);
		
	}
	
}
