package com.masai.controller;

import java.util.List;

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

import com.masai.exception.SeedException;
import com.masai.model.Seed;
import com.masai.service.SeedService;

@RestController
public class SeedController {
	
	@Autowired
	private SeedService sService;
	
	@PostMapping("/seeds")
	public ResponseEntity<Seed> addSeedHandler(@RequestBody Seed seed) throws SeedException{
		
		Seed seeds = sService.addSeed(seed);
		
		return new ResponseEntity<Seed>(seeds, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/seeds")
	public ResponseEntity<Seed> updateSeedHandler(@RequestBody Seed seed) throws SeedException{
		
		Seed seeds = sService.updateSeed(seed);
		
		return new ResponseEntity<Seed>(seeds, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/seeds/{id}")
	public ResponseEntity<Seed> deleteSeedHandler(@PathVariable("id") Integer seedId) throws SeedException{
		
		Seed seeds = sService.deleteSeed(seedId);
		
		return new ResponseEntity<Seed>(seeds, HttpStatus.OK);
		
	}
	
	@GetMapping("/seeds/{id}")
	public ResponseEntity<Seed> viewSeedHandler(@PathVariable("id") Integer seedId) throws SeedException{
		
		Seed seeds = sService.viewSeed(seedId);
		
		return new ResponseEntity<Seed>(seeds, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/seeds/name/{name}")
	public ResponseEntity<List<Seed>> viewSeedHandler2(@PathVariable("name") String name) throws SeedException{
		
		List<Seed> seeds = sService.viewSeed(name);
		
		return new ResponseEntity<List<Seed>>(seeds, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/seeds")
	public ResponseEntity<List<Seed>> viewAllSeedHandler() throws SeedException{
		
		List<Seed> seeds = sService.viewAllSeeds();
		
		return new ResponseEntity<List<Seed>>(seeds, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/seeds/type/{type}")
	public ResponseEntity<List<Seed>> viewAllSeedByTypeHandler(@PathVariable("type") String type) throws SeedException{
		
		List<Seed> seeds = sService.viewAllSeeds(type);
		
		return new ResponseEntity<List<Seed>>(seeds, HttpStatus.OK);
		
	}
	
}
