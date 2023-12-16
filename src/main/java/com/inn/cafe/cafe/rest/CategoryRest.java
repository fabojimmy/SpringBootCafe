package com.inn.cafe.cafe.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inn.cafe.cafe.POJO.Category;

@RequestMapping(value = "category")
public interface CategoryRest {
    
    @PostMapping(value = "categoryAdd")
    public ResponseEntity<String> categoryAdd(@RequestBody(required = true) Map<String,String> RequestCategoryMap);

    @GetMapping(value = "getAllCategory")
    public ResponseEntity<java.util.List<Category>> getAllCategory(@RequestParam(required = false)String filterValue);

    @PostMapping(value = "update")
    public ResponseEntity<String> update(@RequestBody(required = true)Map<String, String> requestMap);
}
