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

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.SeedException;
import com.masai.model.Seed;
import com.masai.service.SeedService;

@RestController
public class SeedController {
	
	 @Autowired
	    private SeedService sService;
	    
	    @PostMapping("/seeds/{user}")
	    public ResponseEntity<Seed> addSeedHandler(@RequestBody Seed seed, @PathVariable String user) throws SeedException, AdminException{
	        
	        Seed seeds = sService.addSeed(seed, user);
	        
	        return new ResponseEntity<Seed>(seeds, HttpStatus.CREATED);
	        
	    }
	    
	    @PutMapping("/seeds/{user}")
	    public ResponseEntity<Seed> updateSeedHandler(@RequestBody Seed seed, @PathVariable String user) throws SeedException, AdminException{
	        
	        Seed seeds = sService.updateSeed(seed, user);
	        
	        return new ResponseEntity<Seed>(seeds, HttpStatus.ACCEPTED);
	        
	    }
	    
	    @DeleteMapping("/seeds/{user}/{id}")
	    public ResponseEntity<Seed> deleteSeedHandler(@PathVariable("id") Integer seedId, @PathVariable String user) throws SeedException, AdminException{
	        
	        Seed seeds = sService.deleteSeed(seedId, user);
	        
	        return new ResponseEntity<Seed>(seeds, HttpStatus.OK);
	        
	    }
	    
	    @GetMapping("/seeds/{user}/{seedId}")
	    public ResponseEntity<Seed> viewSeedHandler(@PathVariable Integer seedId, @PathVariable String user) throws SeedException,AdminException,CustomerException{
	    
	        Seed seeds = sService.viewSeed(seedId,user);
	        
	        return new ResponseEntity<Seed>(seeds, HttpStatus.OK);
	        
	    }
	    
	    @GetMapping("/seedsname/{user}/{name}")
	    public ResponseEntity<List<Seed>> viewSeedHandler2(@PathVariable String name,@PathVariable String user) throws SeedException, AdminException,CustomerException{
	        
	        List<Seed> seeds = sService.viewSeed(name,user);
	        
	        return new ResponseEntity<List<Seed>>(seeds, HttpStatus.OK);
	        
	    }
	    
	    @GetMapping("/seeds/{user}")
	    public ResponseEntity<List<Seed>> viewAllSeedHandler(@PathVariable String user) throws SeedException, AdminException,CustomerException{
	        
	        List<Seed> seeds = sService.viewAllSeeds(user);
	        
	        return new ResponseEntity<List<Seed>>(seeds, HttpStatus.OK);
	        
	    }
	    
	    @GetMapping("/seedstype/{user}/{type}")
	    public ResponseEntity<List<Seed>> viewAllSeedByTypeHandler(@PathVariable String type,@PathVariable String user) throws SeedException, AdminException,CustomerException{
	        
	        List<Seed> seeds = sService.viewAllSeeds(type,user);
	        
	        return new ResponseEntity<List<Seed>>(seeds, HttpStatus.OK);
	        
	    }
	
}
