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

	@PostMapping("/orders/{customer_userName}/{planterId}/{customerId}")
	ResponseEntity<Orders> creatorderhandler(@PathVariable Integer customerId, @PathVariable String customer_userName,@PathVariable Integer planterId, @RequestBody Orders order) throws OrderException, CustomerException {
		Orders neworder = orderservice.addOrder(order,planterId,customerId,customer_userName);
		return new ResponseEntity<>(neworder, HttpStatus.CREATED);

	}

	@PutMapping("/orders/{customer_userName}")
	ResponseEntity<Orders> UpdateOrderHandler(@RequestBody Orders order,@PathVariable String customer_userName) throws OrderException, CustomerException {
		Orders updateorder = orderservice.updateOrder(order,customer_userName);
		return new ResponseEntity<Orders>(updateorder, HttpStatus.OK);
	}

	@DeleteMapping("/orders/{customer_userName}/{BookingOrderId}")
	ResponseEntity<Orders> deleteOrderHandler(@PathVariable("BookingOrderId") Integer BookingOrderId,@PathVariable String customer_userName)
			throws OrderException, CustomerException {
		Orders orderdeleted = orderservice.deleteOrder(BookingOrderId,customer_userName);
		return new ResponseEntity<Orders>(orderdeleted, HttpStatus.OK);
	}

	@GetMapping("/orders/{customer_userName}/{BookingOrderId}")
	ResponseEntity<Orders> viewOrderHandler(@PathVariable("BookingOrderId") Integer BookingOrderId,@PathVariable String customer_userName)
			throws OrderException, CustomerException, AdminException {
		Orders vieworder = orderservice.viewOrder(BookingOrderId,customer_userName);
		return new ResponseEntity<Orders>(vieworder, HttpStatus.OK);
	}

	@GetMapping("/orders/{customer_userName}")
	ResponseEntity<List <Orders>> viewAllOrderHandler(@PathVariable String customer_userName)throws OrderException, CustomerException, AdminException{
		List<Orders> Allorders= orderservice.viewAllOrder(customer_userName);
		return new ResponseEntity<List <Orders>>(Allorders, HttpStatus.OK);
	}

}
