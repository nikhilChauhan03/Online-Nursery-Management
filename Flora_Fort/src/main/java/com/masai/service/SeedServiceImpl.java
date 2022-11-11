package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.SeedException;
import com.masai.model.Seed;
import com.masai.repositry.SeedRepository;


@Service
public class SeedServiceImpl implements SeedService{

	@Autowired
	private SeedRepository sRepo;
	
	
	
	@Override
	public Seed addSeed(Seed seed) {
		return sRepo.save(seed);
	}

	@Override
	public Seed updateSeed(Seed seed)throws SeedException {
		
		Optional<Seed> s = sRepo.findById(seed.getSeedId());
		
		if(s.isPresent())
			return sRepo.save(seed);
		else
			throw new SeedException("No seed exist with the Id : "+seed.getSeedId());
		
	}

	@Override
	public Seed deleteSeed(Integer seedId) throws SeedException{
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
	public Seed viewSeed(int seedId) throws SeedException{
		
		Optional<Seed> s = sRepo.findById(seedId);
		
		return s.orElseThrow( () -> new SeedException("No seed exist with the Id : "+seedId));
	}

	@Override
	public List<Seed> viewSeed(String commonName) throws SeedException {

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
	public List<Seed> viewAllSeeds() throws SeedException {
		List<Seed> list = sRepo.findAll();
		if(list != null)
			return list;
		else
			throw new SeedException("no seed found...!");
	}

	@Override
	public List<Seed> viewAllSeeds(String typeOfSeed) throws SeedException {
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
