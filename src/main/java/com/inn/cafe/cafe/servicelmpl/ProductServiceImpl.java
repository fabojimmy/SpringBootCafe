package com.inn.cafe.cafe.servicelmpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.cafe.cafe.POJO.Category;
import com.inn.cafe.cafe.POJO.Product;
import com.inn.cafe.cafe.dao.CategoryDao;
import com.inn.cafe.cafe.dao.ProductDao;
import com.inn.cafe.cafe.service.ProductService;
import com.inn.cafe.cafe.utils.CafeUtils;
import com.inn.cafe.cafe.wrapper.ProductWrapper;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public ResponseEntity<String> addproduct(Map<String, String> requestMap) {
        // TODO Auto-generated method stub

        try {
            
            productDao.save(this.requertProduct(requestMap, false));
            return CafeUtils.getResponseEntity("Product was Added ", HttpStatus.OK);
        } catch (Exception e) {
          e.printStackTrace();
        }
        throw new UnsupportedOperationException("Unimplemented method 'addproduct'");
    }

    @Override
    public ResponseEntity<String> updateproduct(Map<String, String> requestMap) {

        try {

            Optional<Product> product = productDao.findById(Integer.parseInt(requestMap.get("id")));

            if (!product.isEmpty()) {

                productDao.save(this.requertProduct(requestMap, true));
                
                return CafeUtils.getResponseEntity("Product was Updated ", HttpStatus.OK);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateproduct'");
    }
    
    @Override
    public ResponseEntity<String> deleteproduct(Map<String, String> requestMap) {
        // TODO Auto-generated method stub
        try {
            Optional<Product> product = productDao.findById(Integer.parseInt(requestMap.get("id")));
            productDao.delete(product.get());
            return CafeUtils.getResponseEntity("Product was deleted ", HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
        }
        throw new UnsupportedOperationException("Unimplemented method 'deleteproduct'");
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> allProducts() {
        // TODO Auto-generated method stub

       try {
          return new ResponseEntity<>(productDao.allProducts(),HttpStatus.OK);
       } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
       }
        throw new UnsupportedOperationException("Unimplemented method 'allProducts'");
    }


    private Product requertProduct(Map<String, String>req,Boolean proBoo)
    {
        Product product = new Product();

        if (proBoo) {
            product.setId(Integer.parseInt(req.get("id")));
        }else
        {
            product.setStatus("true");
        }
        product.setName(req.get("name"));
        product.setDescription(req.get("description"));
        product.setPrice(Integer.parseInt(req.get("price")));
        Optional<Category> category=categoryDao.findById(Integer.parseInt(req.get("category_fk")));
        if(!category.isEmpty())
        {
            product.setCategory(category.get());
        }
        

        return product;
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
          try {

            Optional<Product> product = productDao.findById(Integer.parseInt(requestMap.get("id")));
            // Product productreq = new Product();
            if (!product.isEmpty()) {

                // productreq.setId(Integer.parseInt(requestMap.get("id")));
                // productreq.setName(requestMap.get("status"));
                productDao.updateStatus(requestMap.get("status"), product.get().getId());
                
                return CafeUtils.getResponseEntity("Status Product was Updated ", HttpStatus.OK);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatus'");
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
        // TODO Auto-generated method stub
        try {
            
            return new ResponseEntity<List<ProductWrapper>>(productDao.getByCategory(id),HttpStatus.OK);
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
            return new ResponseEntity<List<ProductWrapper>>(productDao.getByProductId(id),HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Unimplemented method 'getByProductId'");
    }
    
}
