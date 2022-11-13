package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.exception.PlanterException;
import com.masai.model.Orders;
import com.masai.model.Planter;
import com.masai.service.PlanterService;

@RestController
public class PlanterController {

	@Autowired
	private PlanterService planterService;

	@GetMapping("/planters/{user}/{id}")
	public ResponseEntity<Planter> viewPlanterHandler(@Valid @PathVariable("id") int planterId,
			@PathVariable("user") String user) throws PlanterException, AdminException, CustomerException {

		Planter obtainedPlanter = planterService.viewPlanter(planterId, user);

		return new ResponseEntity<Planter>(obtainedPlanter, HttpStatus.OK);
	}

	@PutMapping("/planters/{user}")
	ResponseEntity<Planter> UpdatePlanterHandler(@RequestBody Planter planter, @PathVariable String user)
			throws PlanterException, CustomerException, AdminException {
		Planter updatePlanter = planterService.updatePlanter(planter, user);
		return new ResponseEntity<Planter>(updatePlanter, HttpStatus.OK);
	}

	@GetMapping("/plnatershape/{user}/{shape}")
	public ResponseEntity<Planter> viewPlanterByShape(@Valid @PathVariable("shape") String planterShape,
			@PathVariable("user") String user) throws PlanterException, AdminException, CustomerException {

		Planter planter = planterService.viewPlanter(planterShape, user);

		return new ResponseEntity<Planter>(planter, HttpStatus.OK);
	}

	@DeleteMapping("/planters/{user}/{id}")
	public ResponseEntity<Planter> deletePlanterHandler(@PathVariable("id") Planter planter, @PathVariable String user)
			throws PlanterException, CustomerException, AdminException {
		Planter Planterdeleted = planterService.deletePlanter(planter, user);
		return new ResponseEntity<Planter>(Planterdeleted, HttpStatus.OK);
	}

	@GetMapping("/planters/{user}")
	public ResponseEntity<List<Planter>> findAllPlanters(@PathVariable String user)
			throws PlanterException, AdminException, CustomerException {

		List<Planter> planterList = planterService.viewAllPlanters(user);

		return new ResponseEntity<List<Planter>>(planterList, HttpStatus.OK);
	}

	@GetMapping("/planters/{user}/{minCost}/{maxCost}")
	public ResponseEntity<List<Planter>> viewPlantersByCostRange(@Valid @PathVariable("minCost") Integer minCost,
			@PathVariable("maxCost") Integer maxCost, @PathVariable("user") String user)
			throws PlanterException, AdminException, CustomerException {

		List<Planter> planterList = planterService.viewAllPlanters(minCost, maxCost, user);

		return new ResponseEntity<List<Planter>>(planterList, HttpStatus.OK);
	}

	@PostMapping("/plnatersassociate/{user}")
	public ResponseEntity<Planter> regiserPlanterWithSeedAndPlantHandler(@RequestParam Integer planter,
			@RequestParam Integer plantId, @RequestParam Integer seedId, @PathVariable("user") String user)
			throws PlanterException, AdminException {
		return new ResponseEntity<>(planterService.registerPlanter(planter, plantId, seedId, user), HttpStatus.CREATED);
	}

	@PostMapping("/planters/{user}")
	public ResponseEntity<Planter> registerPlanterHandler(@RequestBody Planter planter, @PathVariable String user)
			throws PlanterException, AdminException {
		return new ResponseEntity<>(planterService.addPlanter(planter, user), HttpStatus.CREATED);
	}

}