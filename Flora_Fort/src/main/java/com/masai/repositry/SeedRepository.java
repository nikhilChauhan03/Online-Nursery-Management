package com.masai.repositry;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.masai.model.Seed;

@Repository
public interface SeedRepository extends JpaRepository<Seed, Integer>{

	public Seed findByCommonName(String commonName);
	
	
	//public Seed addSeedHandler(Seed seed);
	
//	public Seed updateSeed(Seed seed);
//	
//	public Seed deleteSeed(Seed seed);
//	
//	public Seed viewSeed(int seedId);
//	
//	public Seed viewSeed(String commonName);
//	
//	public List<Seed> viewAllSeeds();
//	
//	public List<Seed> viewAllSeeds(String typeOfSeed);
//	
//	public List<Seed> findByTypeOfSeed(String typeOfSeed);
//	
}
