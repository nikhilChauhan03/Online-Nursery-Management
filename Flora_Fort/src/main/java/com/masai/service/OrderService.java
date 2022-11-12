package com.masai.service;

import java.util.List;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.model.Customer;
import com.masai.model.Orders;


public interface OrderService {
	public Orders addOrder(Orders order,Integer planterId,Integer customerId, String user) throws OrderException,CustomerException;

	public Orders updateOrder(Orders order, String user) throws OrderException, CustomerException;

	public Orders deleteOrder(int BookingOrderId, String user) throws OrderException, CustomerException;

	public Orders viewOrder(int BookingOrderId, String user) throws OrderException,CustomerException, AdminException;

	public List<Orders> viewAllOrder(String user) throws OrderException,CustomerException,AdminException;

}