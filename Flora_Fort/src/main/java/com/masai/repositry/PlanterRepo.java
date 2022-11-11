package com.masai.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Planter;

@Repository
public interface PlanterRepo  extends JpaRepository<Planter, Integer>{

	

	@Query("select p from Planter p where p.planterShape =?1 ")
	Planter findByPlanterShape(String planterShape);

	@Query("select p from Planter p where p.planterCost between ?1 and ?2")
	List<Planter> findAllByPlanterCostRange(Integer minCost, Integer maxCost);
}
