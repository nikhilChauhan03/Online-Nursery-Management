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

	@GetMapping("/planters/{userName}/{planterId}")
	public ResponseEntity<Planter> viewPlanterHandler(@Valid @PathVariable Integer planterId,
			@PathVariable("user") String userName) throws PlanterException, AdminException, CustomerException {

		Planter obtainedPlanter = planterService.viewPlanter(planterId, userName);

		return new ResponseEntity<Planter>(obtainedPlanter, HttpStatus.OK);
	}

	@PutMapping("/planters/admin_userName}")
	ResponseEntity<Planter> UpdatePlanterHandler(@RequestBody Planter planter, @PathVariable String admin_userName)
			throws PlanterException, CustomerException, AdminException {
		Planter updatePlanter = planterService.updatePlanter(planter, admin_userName);
		return new ResponseEntity<Planter>(updatePlanter, HttpStatus.OK);
	}

	@GetMapping("/plnatershape/{userName}/{shape}")
	public ResponseEntity<Planter> viewPlanterByShape(@Valid @PathVariable("shape") String planterShape,
			@PathVariable("user") String userName) throws PlanterException, AdminException, CustomerException {

		Planter planter = planterService.viewPlanter(planterShape, userName);

		return new ResponseEntity<Planter>(planter, HttpStatus.OK);
	}

	@DeleteMapping("/planters/{admin_userName}/{planterId}")
	public ResponseEntity<Planter> deletePlanterHandler(@PathVariable Integer planterId, @PathVariable String admin_userName)
			throws PlanterException, CustomerException, AdminException {
		Planter Planterdeleted = planterService.deletePlanter(planterId, admin_userName);
		return new ResponseEntity<Planter>(Planterdeleted, HttpStatus.OK);
	}

	@GetMapping("/planters/{userName}")
	public ResponseEntity<List<Planter>> findAllPlanters(@PathVariable String userName)
			throws PlanterException, AdminException, CustomerException {

		List<Planter> planterList = planterService.viewAllPlanters(userName);

		return new ResponseEntity<List<Planter>>(planterList, HttpStatus.OK);
	}

	@GetMapping("/planters/{userName}/{minCost}/{maxCost}")
	public ResponseEntity<List<Planter>> viewPlantersByCostRange(@Valid @PathVariable("minCost") Integer minCost,
			@PathVariable("maxCost") Integer maxCost, @PathVariable("userName") String userName)
			throws PlanterException, AdminException, CustomerException {

		List<Planter> planterList = planterService.viewAllPlanters(minCost, maxCost, userName);

		return new ResponseEntity<List<Planter>>(planterList, HttpStatus.OK);
	}

	@PostMapping("/plnatersassociate/{admin_userName}")
	public ResponseEntity<Planter> regiserPlanterWithSeedAndPlantHandler(@RequestParam Integer planter,
			@RequestParam Integer plantId, @RequestParam Integer seedId, @PathVariable("admin_userName") String admin_userName)
			throws PlanterException, AdminException {
		return new ResponseEntity<>(planterService.registerPlanter(planter, plantId, seedId, admin_userName), HttpStatus.CREATED);
	}

	@PostMapping("/planters/{admin_userName}")
	public ResponseEntity<Planter> registerPlanterHandler(@RequestBody Planter planter, @PathVariable String admin_userName)
			throws PlanterException, AdminException {
		return new ResponseEntity<>(planterService.addPlanter(planter, admin_userName), HttpStatus.CREATED);
	}

}