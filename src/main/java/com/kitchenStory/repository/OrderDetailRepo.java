package com.kitchenStory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kitchenStory.entity.OrderDetail;

@Repository
public interface OrderDetailRepo extends CrudRepository<OrderDetail,Integer>{

}
