package com.masai.service;

import java.util.List;

import com.masai.exception.OrderException;
import com.masai.model.Order;

public interface OrderService {
	public Order addOrder(Order order) throws OrderException;

	public Order updateOrder(Order order) throws OrderException;

	public Order deleteOrder(int BookingOrderId) throws OrderException;

	public Order viewOrder(int BookingOrderId) throws OrderException;

	public List<Order> viewAllOrder() throws OrderException;

}