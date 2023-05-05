package com.kitchenStory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kitchenStory.entity.Product;
import com.kitchenStory.repository.ProductRepo;

@Service
public class ProductService {
	@Autowired
	ProductRepo productRepo;
	
	public Product addNewProduct(Product product) {
		
		return productRepo.save(product);
		 
	}
	public List<Product> getAllProducts(int pageNumber , String searchKey){
		
		Pageable pageable = PageRequest.of(pageNumber,12);
		

        if(searchKey.equals("")) {
            return (List<Product>) productRepo.findAll(pageable);
        } else {
            return (List<Product>) productRepo.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
                    searchKey, searchKey, pageable
            );
        }
	}
	
	public void deleteProductDetails(Integer productId) {
		productRepo.deleteById(productId);
		
	}

    public Product getProductDetailsById(Integer productId) {
        return productRepo.findById(productId).get();
    }
    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
        if(isSingleProductCheckout && productId != 0) {
            // we are going to buy a single product

            List<Product> list = new ArrayList<>();
            Product product = productRepo.findById(productId).get();
            list.add(product);
            return list;
        } else {
        	return new ArrayList<>();
           
        }
    }

}
