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

    @Autowired
    private SeedRepository sRepo;
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private CustomerService customerService;
    
    
    
    @Override
    public Seed addSeed(Seed seed, String user) throws SeedException, AdminException {
        
        if(!adminService.validateAdmin(user)) throw new AdminException("user is not valid...!");
        
        return sRepo.save(seed);
    }
    @Override
    public Seed updateSeed(Seed seed, String user)throws SeedException, AdminException {
        
        if(!adminService.validateAdmin(user)) throw new AdminException("user is not valid...!");
        
        Optional<Seed> s = sRepo.findById(seed.getSeedId());
        
        if(s.isPresent())
            return sRepo.save(seed);
        else
            throw new SeedException("No seed exist with the Id : "+seed.getSeedId());
        
    }
    @Override
    public Seed deleteSeed(Integer seedId, String user)throws SeedException, AdminException{
        if(!adminService.validateAdmin(user)) throw new AdminException("user is not valid...!");
        Optional<Seed> s = sRepo.findById(seedId);
        
        if(s.isPresent()) {
            Seed deletedSeed = s.get();
            sRepo.delete(deletedSeed);
            return deletedSeed;
        }
        else
            throw new SeedException("No seed exist with the Id : "+seedId);
    }
    @Override
    public Seed viewSeed(Integer seedId, String user)throws AdminException, CustomerException, SeedException{
        
        if(adminService.validateAdmin(user) || customerService.validateCustomer(user))
        {
        	 Optional<Seed> s = sRepo.findById(seedId);
             
             return s.orElseThrow( () -> new SeedException("No seed exist with the Id : "+seedId));
        }
        throw new AdminException("user is not valid...!");
   
    }
    
    @Override
    public List<Seed> viewSeed(String commonName, String user)throws SeedException, AdminException, CustomerException{
        if(!(adminService.validateAdmin(user) || customerService.validateCustomer(user)))
            throw new AdminException("user is not valid...!");
        
        
        List<Seed> l = new ArrayList<>();
        boolean b = false;
        List<Seed> list = sRepo.findAll();
        for(Seed s:list) {
            if(s.getCommonName().equals(commonName)) {
                l.add(s);
                b=true;
                break;
            }
        }
        
        if(b)
            return l;
        else
            throw new SeedException("No seed exist with the Id : "+commonName);
        
    }
    @Override
    public List<Seed> viewAllSeeds(String user)throws SeedException, AdminException, CustomerException{
        
    	 if(!(adminService.validateAdmin(user) || customerService.validateCustomer(user)))
            throw new AdminException("user is not valid...!");
        
        
        List<Seed> list = sRepo.findAll();
        if(list != null)
            return list;
        else
            throw new SeedException("no seed found...!");
    }
    @Override
    public List<Seed> viewAllSeeds(String typeOfSeed,String user)throws SeedException, AdminException, CustomerException{
        
    	 if(!(adminService.validateAdmin(user) || customerService.validateCustomer(user)))
            throw new AdminException("user is not valid...!");
        
        List<Seed> l = new ArrayList<>();
        List<Seed> list = sRepo.findAll();
        for(Seed s:list) {
            if(s.getTypeOFSeeds().equals(typeOfSeed)) {
                l.add(s);
            }
        }
        return l;
    }

}
