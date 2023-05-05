package com.kitchenStory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchenStory.configuration.JwtRequestFilter;
import com.kitchenStory.entity.OrderDetail;
import com.kitchenStory.entity.OrderInput;
import com.kitchenStory.entity.OrderProductQuantity;
import com.kitchenStory.entity.Product;
import com.kitchenStory.entity.User;
import com.kitchenStory.repository.OrderDetailRepo;
import com.kitchenStory.repository.ProductRepo;
import com.kitchenStory.repository.Userrepo;

@Service
public class OrderDetailService {
	
    private static final String ORDER_PLACED = "Placed";

	@Autowired
	private OrderDetailRepo orderDetailRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private Userrepo userRepo;
	
	public void placeOrder(OrderInput orderInput) {
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for (OrderProductQuantity o: productQuantityList) {
        	Product product = productRepo.findById(o.getProductId()).get();
        	String currentUser = JwtRequestFilter.CURRENT_USER;
        	User user = userRepo.findById(currentUser).get();
        	  OrderDetail orderDetail = new OrderDetail(
                      orderInput.getFullName(),
                      orderInput.getFullAddress(),
                      orderInput.getContactNumber(),
                      orderInput.getAlternateContactNumber(),
                        ORDER_PLACED,product.getProductDiscountedPrice() * o.getQuantity(),
                        product,user
                        );
        	  orderDetailRepo.save(orderDetail);
        }
	}

}
