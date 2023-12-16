package com.inn.cafe.cafe.servicelmpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.cafe.cafe.POJO.Category;
import com.inn.cafe.cafe.constents.CafeConstants;
import com.inn.cafe.cafe.dao.CategoryDao;
import com.inn.cafe.cafe.jwt.JwtFilter;
import com.inn.cafe.cafe.service.CategoryService;
import com.inn.cafe.cafe.utils.CafeUtils;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> categoryAdd(Map<String, String> category) {
        // TODO Auto-generated method stub

        try {
            if(jwtFilter.isAdmin())
            {
                if(validateCategoryMap(category,false))
                {
                    categoryDao.save(getCategoryFromMap(category, false));
                    return CafeUtils.getResponseEntity("Category Was added", HttpStatus.OK);
                }
            }else
            {

                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED) ;
            }
             
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG+"okk", HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

    private boolean validateCategoryMap(Map<String, String> category, boolean validateId) {

        if(category.containsKey("name"))
        {
            if(category.containsKey("id")&& validateId)
            {
                return true;
            }
            else if(!validateId)
            {
                return true;
            }
        }
        return false;
    }
    
    private Category getCategoryFromMap(Map<String, String> requestMap,Boolean isAdd)
    {
        Category category = new Category();

        if(isAdd) {

            category.setId(Integer.parseInt(requestMap.get("id")));
        }
        
        category.setName(requestMap.get("name"));
        return category;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {

        try {
            // filterValue="true";
            // System.out.println(String.format(filterValue).equalsIgnoreCase("true")+""+categoryDao.findAll());
            System.out.println(filterValue);
            if(filterValue.equalsIgnoreCase("true"))
            {
                return new ResponseEntity<List<Category>>( categoryDao.getAllCategory(),HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<List<Category>>( categoryDao.findAll(),HttpStatus.OK);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        return new ResponseEntity<List<Category>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {

        try {
            if(jwtFilter.isAdmin())
            {
                if(validateCategoryMap(requestMap,true))
                {
                    Optional<Category> category = categoryDao.findById(Integer.parseInt(requestMap.get("id")));

                    if(!category.isEmpty())
                    {

                        categoryDao.save(getCategoryFromMap(requestMap, true));
                        return CafeUtils.getResponseEntity("Category was updated", HttpStatus.OK);
                    }
                    else
                    {
                        return CafeUtils.getResponseEntity("Category id does not exist", HttpStatus.BAD_REQUEST);
                    }
                }

                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
            else{
                return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
