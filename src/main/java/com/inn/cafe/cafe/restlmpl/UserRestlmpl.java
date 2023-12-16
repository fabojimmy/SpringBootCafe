package com.inn.cafe.cafe.restlmpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.cafe.constents.CafeConstants;
import com.inn.cafe.cafe.rest.UserRest;
import com.inn.cafe.cafe.service.UserService;
import com.inn.cafe.cafe.utils.CafeUtils;
import com.inn.cafe.cafe.wrapper.UserWrapper;

@RestController
public class UserRestlmpl implements UserRest{

    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        // TODO Auto-generated method stub
        
        try {
            
            return userService.signUp(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        // TODO Auto-generated method stub

        System.out.println(requestMap.get("email"));

        try {
            return userService.login(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
                }
        throw new UnsupportedOperationException("Unimplemented method 'login'"+requestMap.get("email"));
    }
    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        // TODO Auto-generated method stub

        System.out.println("retour");

        try {

            return userService.getAllUsers();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // @Override
    // public ResponseEntity<String> getTest() {
    //     // TODO Auto-generated method stub
    //     try {
    @Override
    public ResponseEntity<String> update(Map<String, String> resquestMap) {

        
        try {

          return  userService.update(resquestMap);
         } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
         }
         return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        // TODO Auto-generated method stub
    }
    @Override
    public ResponseEntity<String> checkToken() {
        // TODO Auto-generated method stub

        try {
            
            return userService.checkToken();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return  CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        // TODO Auto-generated method stub

        try {
            
            return userService.changePassword(requestMap);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    return  CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {

        try {
            
            return userService.forgotPassword(requestMap);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        // TODO Auto-generated method stub
    }
            
    //         return new ResponseEntity<String>("Bonjour",HttpStatus.ACCEPTED);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return new ResponseEntity<String>("Error juste",HttpStatus.ACCEPTED);
    // }
    
}
