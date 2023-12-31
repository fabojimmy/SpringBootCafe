package com.inn.cafe.cafe.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inn.cafe.cafe.POJO.Product;
import com.inn.cafe.cafe.wrapper.ProductWrapper;


@RequestMapping(path = "/product")
public interface ProductRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> addproduct(@RequestBody(required=true)Map<String,String>requestMap);
    
    @PostMapping(path = "/update")
    public ResponseEntity<String> updateproduct(@RequestBody(required=true)Map<String,String>requestMap);

    @PostMapping(path = "/delete")
    public ResponseEntity<String> deleteproduct(@RequestBody(required=true)Map<String,String>requestMap);

    @GetMapping(path = "/allProducts")
    public ResponseEntity<List<ProductWrapper>> allProducts();

    @PostMapping(path = "/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestBody(required=true)Map<String,String>requestMap);

    @GetMapping(path = "/getByCategory/{id}")
    public ResponseEntity<List<ProductWrapper>>getByCategory(@PathVariable Integer id);
    
    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<List<ProductWrapper>>getByProductId(@PathVariable Integer id);
    
}
