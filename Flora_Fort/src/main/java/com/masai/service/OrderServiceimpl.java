package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.Planter;
import com.masai.repositry.CustomerRepository;
import com.masai.repositry.OrderRepository;
import com.masai.repositry.PlanterRepository;

@Service
public class OrderServiceimpl implements OrderService {

//	IN EVERY METHOD WE NEED TO PROVIDE USERNAME TO AUTHENTICATE THE USER-------
	
/*
 * 		Order repository :we need to  insert, update, delete and get the Order object
 * 		Planter repository : we need to select planter on which customer made order.
 * 		Admin repository : authenticate admin
 * 		Customer repository : authenticate cutomer.
 */
	
	@Autowired
	OrderRepository orderRepositor;
	
	@Autowired 
	CustomerService customerService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	PlanterRepository plnaterRepository;
	
	
	
//	 ---------------------------adding orders to the database we need to have planter id, customerid, and order object----------------------------------------
	
	@Override
	public Orders addOrder(Orders order,Integer planterId,Integer customerId, String user) 
			throws OrderException, CustomerException {
		
		if(!customerService.validateCustomer(user))
			throw new CustomerException("user is not valid or not logged in");
		
		if(order.getBookingOrderId() != null) 
			throw new OrderException("BookinOrderId is not requried");
		
		Planter planter = plnaterRepository.findById(planterId)
		.orElseThrow(() -> new OrderException("Invalid planter Id"));

		Customer customer = customerRepository.findById(customerId)
							.orElseThrow(() -> new CustomerException("Invalid customer Id"));
		
		customer.getOrders().add(order);
		
		order.setCustomer(customer);
		
		order.setPlanters(planter);
		
		planter.getOrders().add(order);
		
		return orderRepositor.save(order);
		
	}

	
//	--------------------------------------------------------------------updating order-------------------------------------------------------------------------
	
	@Override
	public Orders updateOrder(Orders order,String user) throws OrderException, CustomerException {
		
		if(!customerService.validateCustomer(user))throw new CustomerException("user is not valid or not logged in");
		
		Orders existingOrder = orderRepositor.findById(order.getBookingOrderId())
				.orElseThrow(() -> new OrderException(
						"Invalid Order details. Please provide the valid details. Booking Order Id is not vlaid"));

	
		return orderRepositor.save(existingOrder);
	}

	
//	-------------------------------------------------------------------delete order-------------------------------------------------------------------------------
	
	@Override
	public Orders deleteOrder(int BookingOrderId,String user) throws OrderException, CustomerException {
		
		if(!customerService.validateCustomer(user))
			throw new CustomerException("user is not valid or not logged in");
		
		Optional<Orders> optional = orderRepositor.findById(BookingOrderId);
		
		if (optional.isPresent()) {
			
			Orders order = optional.get();
			orderRepositor.delete(order);
			return order;
			
		} else {
			throw new OrderException("order does not exists with " + BookingOrderId);
		}

	}

	
//	--------------------------------------------------------------view perticular order by order id-------------------------------------------------------------
	
	@Override
	public Orders viewOrder(int BookingOrderId,String user) throws OrderException, CustomerException, AdminException {
		
		if(!(customerService.validateCustomer(user) || adminService.validateAdmin(user)))
			throw new CustomerException("user is not valid or not logged in");
		
		Optional<Orders> optional = orderRepositor.findById(BookingOrderId);
		
		if(optional.isPresent()) {
			
		Orders order = optional.get();
			return order;
			
		} else {
			throw new OrderException("order does not exists with " + BookingOrderId);
		}
		
	}

	
//	-------------------------------------------------------------------view all orders made by customers-------------------------------------------------------------
	
	@Override
	public List<Orders> viewAllOrder(String user) throws OrderException, CustomerException, AdminException {
		
		if(!(adminService.validateAdmin(user)))throw new CustomerException("Admin is not valid or not logged in");
		
		List<Orders> orders = orderRepositor.findAll();
		
		if(orders.isEmpty()) throw new OrderException("No order is registered in database");
		
		return orders;
		
	}

	
	
	
}
