package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.OrderException;
import com.masai.model.Order;
import com.masai.repositry.OrderDao;

@Service
public class OrderServiceimpl implements OrderService {

	@Autowired
	OrderDao orderdao;

	@Override
	public Order addOrder(Order order) throws OrderException {
		// TODO Auto-generated method stub
		Optional<Order> optional = orderdao.findById(order.getBookingOrderId());
		if (optional.isPresent()) {
			throw new OrderException("order already exists...." + order.getBookingOrderId());
		} else {
			orderdao.save(order);
			return order;
		}

	}

	@Override
	public Order updateOrder(Order order) throws OrderException {
		Order existingOrder = orderdao.findById(order.getBookingOrderId())
				.orElseThrow(() -> new OrderException(
						"Invalid Order details. Please provide the valid details. Booking Order Id is not vlaid"));

	
		return orderdao.save(existingOrder);
	}

	@Override
	public Order deleteOrder(int BookingOrderId) throws OrderException {
		// TODO Auto-generated method stub
		Optional<Order> optional = orderdao.findById(BookingOrderId);
		if (optional.isPresent()) {
			Order order = optional.get();
			orderdao.delete(order);
			return order;
		} else {
			throw new OrderException("order does not exists with " + BookingOrderId);
		}

	}

	@Override
	public Order viewOrder(int BookingOrderId) throws OrderException {
		Optional<Order> optional = orderdao.findById(BookingOrderId);
		if(optional.isPresent()) {
		Order order = optional.get();
			return order;
		} else {
			throw new OrderException("order does not exists with " + BookingOrderId);
		}
	}

	@Override
	public List<Order> viewAllOrder() throws OrderException {
		// TODO Auto-generated method stub
		List<Order> orders = orderdao.findAll();
		if(orders.isEmpty()) throw new OrderException("No customer is registered in database");
		return orders;
	}

}
