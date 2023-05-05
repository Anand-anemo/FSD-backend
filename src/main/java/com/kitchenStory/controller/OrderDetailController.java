package com.kitchenStory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kitchenStory.entity.OrderInput;
import com.kitchenStory.service.OrderDetailService;

@RestController
public class OrderDetailController {
	@Autowired
	private OrderDetailService orderDetailService;
	
	
    //Api to place order for user
	@PreAuthorize("hasRole('User')")
	@PostMapping({"/placeOrder"})
	public void placeOrder(@RequestBody OrderInput orderInput) {
		orderDetailService.placeOrder(orderInput);
	}
	

}
