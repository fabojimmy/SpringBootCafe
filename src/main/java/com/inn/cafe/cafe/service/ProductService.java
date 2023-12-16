package com.inn.cafe.cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.inn.cafe.cafe.POJO.Product;
import com.inn.cafe.cafe.wrapper.ProductWrapper;

public interface ProductService {

   public ResponseEntity<String> addproduct(Map<String, String> requestMap);

   public ResponseEntity<String> updateproduct(Map<String, String> requestMap);

   public ResponseEntity<String> deleteproduct(Map<String, String> requestMap);

   public ResponseEntity<List<ProductWrapper>> allProducts();

    public ResponseEntity<String> updateStatus(Map<String, String> requestMap);

    public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id);

    public ResponseEntity<List<ProductWrapper>> getByProductId(Integer id);
    
}
