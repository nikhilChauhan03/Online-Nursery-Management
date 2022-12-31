package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.SeedException;
import com.masai.model.Seed;
import com.masai.repositry.SeedRepository;


@Service
public class SeedServiceImpl implements SeedService{

//	we need to supply user name for authentication for every user :--------------
	
	
//	take seed repositry, admin service, customer service instance variable so that we can validate customer and admin :-----------
	
	
    @Autowired
    private SeedRepository seedRepositry;
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private CustomerService customerService;
    
    
//   adding seed to the database :--------------------------------------
    
    @Override
    public Seed addSeed(Seed seed, String user) throws SeedException, AdminException {
        
        if(!adminService.validateAdmin(user)) throw new AdminException("user is not valid...!");
        
        return seedRepositry.save(seed);
    }
    
//    updatin seed by taking seed from request body :---------------------------------
    
    @Override
    public Seed updateSeed(Seed seed, String user)throws SeedException, AdminException {
        
        if(!adminService.validateAdmin(user)) throw new AdminException("user is not valid...!");
        
        Optional<Seed> optinalSeed = seedRepositry.findById(seed.getSeedId());
        
        if(optinalSeed.isPresent())
            return seedRepositry.save(seed);
        else
            throw new SeedException("No seed exist with the Id : "+seed.getSeedId());
        
    }
    
//    deleting seed by seed id :--------------------------
    
    @Override
    public Seed deleteSeed(Integer seedId, String user)throws SeedException, AdminException{
        if(!adminService.validateAdmin(user)) throw new AdminException("user is not valid...!");
        Optional<Seed> optinalSeed = seedRepositry.findById(seedId);
        
        if(optinalSeed.isPresent()) {
            Seed deletedSeed = optinalSeed.get();
            seedRepositry.delete(deletedSeed);
            return deletedSeed;
        }
        else
            throw new SeedException("No seed exist with the Id : "+seedId);
    }
    
    
//    view perticular seed by seed id :---------------------
    
    @Override
    public Seed viewSeed(Integer seedId, String user)throws AdminException, CustomerException, SeedException{
        
        if(adminService.validateAdmin(user) || customerService.validateCustomer(user))
        {
        	 Optional<Seed> optinalSeed = seedRepositry.findById(seedId);
             
             return optinalSeed.orElseThrow( () -> new SeedException("No seed exist with the Id : "+seedId));
        }
        throw new AdminException("user is not valid...!");
   
    }
    
    
//    view all seed by common name of the seed :-----------------
    
    @Override
    public List<Seed> viewSeed(String commonName, String user)throws SeedException, AdminException, CustomerException{
        if(!(adminService.validateAdmin(user) || customerService.validateCustomer(user)))
            throw new AdminException("user is not valid...!");
        
        
        List<Seed> seeds = new ArrayList<>();
        boolean check = false;
        List<Seed> list = seedRepositry.findAll();
        for(Seed seed:list) {
            if(seed.getCommonName().equals(commonName)) {
            	seeds.add(seed);
                check=true;
                break;
            }
        }
        
        if(check)
            return seeds;
        else
            throw new SeedException("No seed exist with the Id : "+commonName);
        
    }
    
//    view all seed if user is authenticated :----------------------------
    
    @Override
    public List<Seed> viewAllSeeds(String user)throws SeedException, AdminException, CustomerException{
        
    	 if(!(adminService.validateAdmin(user) || customerService.validateCustomer(user)))
            throw new AdminException("user is not valid...!");
        
        
        List<Seed> seeds = seedRepositry.findAll();
        if(seeds != null)
            return seeds;
        else
            throw new SeedException("Seeds not avaialbe this time...!");
    }
    
//    view all seeds of perticular type by entering some specifinc type of seeds :-------------------
    
    @Override
    public List<Seed> viewAllSeeds(String typeOfSeed,String user)throws SeedException, AdminException, CustomerException{
        
    	 if(!(adminService.validateAdmin(user) || customerService.validateCustomer(user)))
            throw new AdminException("user is not valid...!");
        
        List<Seed> seeds = new ArrayList<>();
        List<Seed> availableSeeds = seedRepositry.findAll();
        for(Seed seed:availableSeeds) {
            if(seed.getTypeOFSeeds().equals(typeOfSeed)) {
                seeds.add(seed);
            }
        }
        return seeds;
    }

}
