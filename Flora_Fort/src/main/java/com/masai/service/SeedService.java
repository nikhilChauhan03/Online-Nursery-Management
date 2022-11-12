package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.SeedException;
import com.masai.model.Seed;


public interface SeedService {

public Seed addSeed(Seed seed, String user) throws SeedException, AdminException;
    
    public Seed updateSeed(Seed seed,String user) throws SeedException, AdminException;
    
    public Seed deleteSeed(Integer seedId, String user) throws SeedException, AdminException;
    
    public Seed viewSeed(Integer seedId, String user) throws SeedException, AdminException, CustomerException;
    
    public List<Seed> viewSeed(String commonName, String user) throws SeedException, AdminException, CustomerException;
    
    public List<Seed> viewAllSeeds(String user) throws SeedException, AdminException, CustomerException;
    
    public List<Seed> viewAllSeeds(String typeOfSeedString,String user) throws SeedException, AdminException, CustomerException;
	
}
