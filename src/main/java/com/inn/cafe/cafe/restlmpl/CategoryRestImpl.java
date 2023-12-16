package com.inn.cafe.cafe.restlmpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.cafe.POJO.Category;
import com.inn.cafe.cafe.constents.CafeConstants;
import com.inn.cafe.cafe.rest.CategoryRest;
import com.inn.cafe.cafe.service.CategoryService;
import com.inn.cafe.cafe.utils.CafeUtils;

@RestController
public class CategoryRestImpl implements CategoryRest{

    @Autowired
    CategoryService categoryService;

    @Override
    public ResponseEntity<String> categoryAdd(Map<String, String> RequestCategoryMap) {

        try {
           return categoryService.categoryAdd(RequestCategoryMap);
            // return CafeUtils.getResponseEntity("Category Was added", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG+",auvas", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        // TODO Auto-generated method stub

        try {
            return categoryService.getAllCategory(filterValue);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        // TODO Auto-generated method stub

        try {
           return categoryService.update(requestMap);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
