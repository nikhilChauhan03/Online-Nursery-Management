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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.model.Orders;
import com.masai.service.OrderService;


@RestController
public class OrderController {
	@Autowired
	OrderService orderservice;

	@PostMapping("/orders/{user}/{planterId}/{customerId}")
	ResponseEntity<Orders> creatorderhandler(@PathVariable Integer customerId, @PathVariable String user,@PathVariable Integer planterId, @RequestBody Orders order) throws OrderException, CustomerException {
		Orders neworder = orderservice.addOrder(order,planterId,customerId,user);
		return new ResponseEntity<>(neworder, HttpStatus.CREATED);

	}

	@PutMapping("/orders/{user}")
	ResponseEntity<Orders> UpdateOrderHandler(@RequestBody Orders order,@PathVariable String user) throws OrderException, CustomerException {
		Orders updateorder = orderservice.updateOrder(order,user);
		return new ResponseEntity<Orders>(updateorder, HttpStatus.OK);
	}

	@DeleteMapping("/orders/{user}/{BookingOrderId}")
	ResponseEntity<Orders> deleteOrderHandler(@PathVariable("BookingOrderId") Integer BookingOrderId,@PathVariable String user)
			throws OrderException, CustomerException {
		Orders orderdeleted = orderservice.deleteOrder(BookingOrderId,user);
		return new ResponseEntity<Orders>(orderdeleted, HttpStatus.OK);
	}

	@GetMapping("/orders/{user}/{BookingOrderId}")
	ResponseEntity<Orders> viewOrderHandler(@PathVariable("BookingOrderId") Integer BookingOrderId,@PathVariable String user)
			throws OrderException, CustomerException, AdminException {
		Orders vieworder = orderservice.viewOrder(BookingOrderId,user);
		return new ResponseEntity<Orders>(vieworder, HttpStatus.OK);
	}

	@GetMapping("/orders/{user}")
	ResponseEntity<List <Orders>> viewAllOrderHandler(@PathVariable String user)throws OrderException, CustomerException, AdminException{
		List<Orders> Allorders= orderservice.viewAllOrder(user);
		return new ResponseEntity<List <Orders>>(Allorders, HttpStatus.OK);
	}

}
