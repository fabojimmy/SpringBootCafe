package com.inn.cafe.cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.inn.cafe.cafe.POJO.Category;

public interface CategoryService {
   public ResponseEntity<String> categoryAdd(Map<String, String> category);
   public ResponseEntity<List<Category>>getAllCategory(String filterValue);
public ResponseEntity<String> update(Map<String, String> requestMap);
    
} 
