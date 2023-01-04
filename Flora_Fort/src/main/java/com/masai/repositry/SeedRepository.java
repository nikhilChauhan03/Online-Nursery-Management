package com.masai.repositry;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.masai.model.Seed;

@Repository
public interface SeedRepository extends JpaRepository<Seed, Integer>{

	public Seed findByCommonName(String commonName);
 
}
