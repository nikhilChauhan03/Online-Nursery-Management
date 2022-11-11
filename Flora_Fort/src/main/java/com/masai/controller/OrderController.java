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
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.OrderException;
import com.masai.model.Order;
import com.masai.service.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class OrderController {
	@Autowired
	OrderService orderservice;

	@PostMapping("/create-order")
	ResponseEntity<Order> creatorderhandler(@RequestBody Order order) throws OrderException {
		Order neworder = orderservice.addOrder(order);
		return new ResponseEntity<>(neworder, HttpStatus.CREATED);

	}

	@PutMapping("/update-order")
	ResponseEntity<Order> UpdateOrderHandler(@RequestBody Order order) throws OrderException {
		Order updateorder = orderservice.updateOrder(order);
		return new ResponseEntity<Order>(updateorder, HttpStatus.OK);
	}

	@DeleteMapping("/delete-order/{BookingOrderId}")
	ResponseEntity<Order> deleteOrderHandler(@PathVariable("BookingOrderId") Integer BookingOrderId)
			throws OrderException {
		Order orderdeleted = orderservice.deleteOrder(BookingOrderId);
		return new ResponseEntity<Order>(orderdeleted, HttpStatus.OK);
	}

	@GetMapping("/view-order/{BookingOrderId}")
	ResponseEntity<Order> viewOrderHandler(@PathVariable("BookingOrderId") Integer BookingOrderId)
			throws OrderException {
		Order vieworder = orderservice.viewOrder(BookingOrderId);
		return new ResponseEntity<Order>(vieworder, HttpStatus.OK);
	}

	@GetMapping("/view-Allorder")
	ResponseEntity<List <Order>> viewAllOrderHandler()throws OrderException{
		List<Order> Allorders= orderservice.viewAllOrder();
		return new ResponseEntity<List <Order>>(Allorders, HttpStatus.OK);
	}

}
