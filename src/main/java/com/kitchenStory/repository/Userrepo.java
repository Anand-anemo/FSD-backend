package com.kitchenStory.repository;

import org.springframework.data.repository.CrudRepository;

import com.kitchenStory.entity.User;

public interface Userrepo extends CrudRepository <User , String> {

}
