package com.inn.cafe.cafe.restlmpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.cafe.POJO.Product;
import com.inn.cafe.cafe.constents.CafeConstants;
import com.inn.cafe.cafe.rest.ProductRest;
import com.inn.cafe.cafe.service.ProductService;
import com.inn.cafe.cafe.utils.CafeUtils;
import com.inn.cafe.cafe.wrapper.ProductWrapper;

@RestController
public class ProductRestImpl implements ProductRest{

    @Autowired
    ProductService productService;

    @Override
    public ResponseEntity<String> addproduct(Map<String, String> requestMap) {

        
        try {
            System.out.println(requestMap.get("name"));
            return productService.addproduct(requestMap);
            
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

        return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
    }
    
    @Override
    public ResponseEntity<String> updateproduct(Map<String, String> requestMap) {
        // TODO Auto-generated method stub
        
        try {
            return productService.updateproduct(requestMap);
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
    }
    
    @Override
    public ResponseEntity<String> deleteproduct(Map<String, String> requestMap) {
        // TODO Auto-generated method stub
        try {
            return productService.deleteproduct(requestMap);
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> allProducts() {
        try {
            System.out.println("okkk");
           return productService.allProducts();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'allProducts'");
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        // TODO Auto-generated method stub
        try {
            return productService.updateStatus(requestMap);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Unimplemented method 'updateStatus'");
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
        // TODO Auto-generated method stub
        System.out.println(id);
        try {
            return productService.getByCategory(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Unimplemented method 'getByCategory'");
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getByProductId(Integer id) {
        // TODO Auto-generated method stub
        try {
           return productService.getByProductId(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Unimplemented method 'getByProductId'");
    }

    
    
}
