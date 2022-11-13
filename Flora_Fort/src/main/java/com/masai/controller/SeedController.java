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
	    
	    @PostMapping("/seeds/{admin_userName}")
	    public ResponseEntity<Seed> addSeedHandler(@RequestBody Seed seed, @PathVariable String admin_userName) throws SeedException, AdminException{
	        
	        Seed seeds = sService.addSeed(seed, admin_userName);
	        
	        return new ResponseEntity<Seed>(seeds, HttpStatus.CREATED);
	        
	    }
	    
	    @PutMapping("/seeds/{admin_userName}")
	    public ResponseEntity<Seed> updateSeedHandler(@RequestBody Seed seed, @PathVariable String admin_userName) throws SeedException, AdminException{
	        
	        Seed seeds = sService.updateSeed(seed, admin_userName);
	        
	        return new ResponseEntity<Seed>(seeds, HttpStatus.ACCEPTED);
	        
	    }
	    
	    @DeleteMapping("/seeds/{admin_userName}/{id}")
	    public ResponseEntity<Seed> deleteSeedHandler(@PathVariable("id") Integer seedId, @PathVariable String admin_userName) throws SeedException, AdminException{
	        
	        Seed seeds = sService.deleteSeed(seedId, admin_userName);
	        
	        return new ResponseEntity<Seed>(seeds, HttpStatus.OK);
	        
	    }
	    
	    @GetMapping("/seeds/{user}/{seedId}")
	    public ResponseEntity<Seed> viewSeedHandler(@PathVariable Integer seedId, @PathVariable String user) throws SeedException,AdminException,CustomerException{
	    
	        Seed seeds = sService.viewSeed(seedId,user);
	        
	        return new ResponseEntity<Seed>(seeds, HttpStatus.OK);
	        
	    }
	    
	    @GetMapping("/seedsname/{userName}/{seed_commonName}")
	    public ResponseEntity<List<Seed>> viewSeedHandler2(@PathVariable String seed_commonName,@PathVariable String userName) throws SeedException, AdminException,CustomerException{
	        
	        List<Seed> seeds = sService.viewSeed(seed_commonName,userName);
	        
	        return new ResponseEntity<List<Seed>>(seeds, HttpStatus.OK);
	        
	    }
	    
	    @GetMapping("/seeds/{userName}")
	    public ResponseEntity<List<Seed>> viewAllSeedHandler(@PathVariable String userName) throws SeedException, AdminException,CustomerException{
	        
	        List<Seed> seeds = sService.viewAllSeeds(userName);
	        
	        return new ResponseEntity<List<Seed>>(seeds, HttpStatus.OK);
	        
	    }
	    
	    @GetMapping("/seedstype/{userName}/{seed_type}")
	    public ResponseEntity<List<Seed>> viewAllSeedByTypeHandler(@PathVariable String seed_type,@PathVariable String userName) throws SeedException, AdminException,CustomerException{
	        
	        List<Seed> seeds = sService.viewAllSeeds(seed_type,userName);
	        
	        return new ResponseEntity<List<Seed>>(seeds, HttpStatus.OK);
	        
	    }
	
}
