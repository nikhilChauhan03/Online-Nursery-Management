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
import com.masai.repositry.CustomerRepositry;
import com.masai.repositry.OrderDao;
import com.masai.repositry.PlanterRepo;

@Service
public class OrderServiceimpl implements OrderService {

	@Autowired
	OrderDao orderdao;
	
	@Autowired 
	CustomerService customerService;
	
	@Autowired
	CustomerRepositry customerRepositry;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	PlanterRepo plnaterRepo;
	
	@Override
	public Orders addOrder(Orders order,Integer planterId,Integer customerId, String user) throws OrderException, CustomerException {
		
		if(!customerService.validateCustomer(user))throw new CustomerException("user is not valid or not logged in");
		
		if(order.getBookingOrderId() != null) throw new OrderException("BookinOrderId is not requried");
		
		Planter planter = plnaterRepo.findById(planterId)
		.orElseThrow(() -> new OrderException("Invalid planter Id"));

		Customer customer = customerRepositry.findById(customerId)
							.orElseThrow(() -> new CustomerException("Invalid customer Id"));
		
		customer.getOrders().add(order);
		
		order.setCustomer(customer);
		
		order.setPlanters(planter);
		
		planter.getOrders().add(order);
		
		return orderdao.save(order);
		

	}

	@Override
	public Orders updateOrder(Orders order,String user) throws OrderException, CustomerException {
		if(!customerService.validateCustomer(user))throw new CustomerException("user is not valid or not logged in");
		Orders existingOrder = orderdao.findById(order.getBookingOrderId())
				.orElseThrow(() -> new OrderException(
						"Invalid Order details. Please provide the valid details. Booking Order Id is not vlaid"));

	
		return orderdao.save(existingOrder);
	}

	@Override
	public Orders deleteOrder(int BookingOrderId,String user) throws OrderException, CustomerException {
		if(!customerService.validateCustomer(user))throw new CustomerException("user is not valid or not logged in");
		Optional<Orders> optional = orderdao.findById(BookingOrderId);
		if (optional.isPresent()) {
			Orders order = optional.get();
			orderdao.delete(order);
			return order;
		} else {
			throw new OrderException("order does not exists with " + BookingOrderId);
		}

	}

	@Override
	public Orders viewOrder(int BookingOrderId,String user) throws OrderException, CustomerException, AdminException {
		if(!(customerService.validateCustomer(user) || adminService.validateAdmin(user)))throw new CustomerException("user is not valid or not logged in");
		Optional<Orders> optional = orderdao.findById(BookingOrderId);
		if(optional.isPresent()) {
		Orders order = optional.get();
			return order;
		} else {
			throw new OrderException("order does not exists with " + BookingOrderId);
		}
	}

	@Override
	public List<Orders> viewAllOrder(String user) throws OrderException, CustomerException, AdminException {
		if(!(customerService.validateCustomer(user) || adminService.validateAdmin(user)))throw new CustomerException("user is not valid or not logged in");
		List<Orders> orders = orderdao.findAll();
		if(orders.isEmpty()) throw new OrderException("No order is registered in database");
		return orders;
	}

}
