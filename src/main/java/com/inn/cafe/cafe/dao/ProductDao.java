package com.inn.cafe.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.inn.cafe.cafe.POJO.Product;
import com.inn.cafe.cafe.wrapper.ProductWrapper;

import jakarta.transaction.Transactional;

public interface ProductDao extends JpaRepository<Product,Integer>{

    public List<ProductWrapper>allProducts();

    @Transactional
    @Modifying
    public Integer updateStatus(@Param("status")String status, @Param("id")Integer id);

    public List<ProductWrapper> getByCategory(@Param("id")Integer id);

    public List<ProductWrapper> getByProductId(@Param("id")Integer id);
    
}
