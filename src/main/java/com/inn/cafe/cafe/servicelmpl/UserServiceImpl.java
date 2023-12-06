package com.inn.cafe.cafe.servicelmpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inn.cafe.cafe.POJO.User;
import com.inn.cafe.cafe.constents.CafeConstants;
import com.inn.cafe.cafe.dao.UserDao;
import com.inn.cafe.cafe.jwt.CustomerUsersDetailsService;
import com.inn.cafe.cafe.jwt.JwtFilter;
import com.inn.cafe.cafe.jwt.JwtUtil;
import com.inn.cafe.cafe.service.UserService;
import com.inn.cafe.cafe.utils.CafeUtils;
import com.inn.cafe.cafe.utils.EmailUtils;
import com.inn.cafe.cafe.wrapper.UserWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;
    
    @Autowired
    EmailUtils emailUtils;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
            log.info("Inside signup{}",requestMap);
        try{
            if(validateSignUpMap(requestMap)){
                    User user=userDao.findByEmailId(requestMap.get("email"));

                    if (Objects.isNull(user)) {
                        System.out.println("User dsds found"+requestMap.get("email"));
                         userDao.save(getUserFromMap(requestMap));
                         return CafeUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                    } else {
                        return CafeUtils.getResponseEntity("Email already exits",HttpStatus.BAD_REQUEST);
                    }
            }else{
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private boolean validateSignUpMap(Map<String,String> requestMap){

       if( requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email")
        && requestMap.containsKey("password") ){

            return true;
        }
            return false;
        
    }

    private User getUserFromMap(Map<String,String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(new BCryptPasswordEncoder().encode(requestMap.get("password")));
        user.setRole(requestMap.get("role"));
        user.setStatus(requestMap.get("status"));
        return user;
    }
    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        // TODO Auto-generated method 
        log.info("Inside login");
        // UserDetails userDetails = new CustomerUsersDetailsService().loadUserByUsername(requestMap.get("name"));

        try {
            final Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
                );
                
                System.out.println(requestMap.get("email"));
            if (authentication.isAuthenticated()) {
                
                System.out.println("Ok parfait");

                if (customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
                    
                    return new ResponseEntity<String>("{\"token\":\"" +jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(), customerUsersDetailsService.getUserDetail().getRole())+"\"}",HttpStatus.OK);
                }
            }
            else{
                return new ResponseEntity<String>("{\"message\":\""+"wait for admin approval"+"\"}",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // TODO: handle exception
            log.error("{}", e);
        }
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        // TODO Auto-generated method stub
        System.out.println("retour");
        try {
            
            if(jwtFilter.isAdmin())
            {
                 return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    public ResponseEntity<String> update(Map<String,String> requestMap) {
        // TODO Auto-generated method stub
         try {

            if (jwtFilter.isAdmin()) {
                 Optional<User> user=userDao.findById(Integer.parseInt(requestMap.get("id")));

                 if (!user.isEmpty()) {
                    // System.out.println(user.get().getStatus());
                    userDao.updateStatus(requestMap.get("status"), user.get().getId());
                    sendMailToAllAdmin(requestMap.get("id"),user.get().getEmail(),userDao.getAllAdmin());
                    return CafeUtils.getResponseEntity("User Status Updated Successfully", HttpStatus.OK);
                 }else{
                   return CafeUtils.getResponseEntity("User id doesn't exist", HttpStatus.OK);
                 }
                 
            }
            else{

                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }

         } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
         }
         return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        // allAdmin.remove(jwtFilter.getCurrentUser());
        // if(status!=null && status.equalsIgnoreCase("true"))
        // {
        //     emailUtils.sendSmpleMessage(jwtFilter.getCurrentUser(),"Account Approved","USER:-"+ user +"\n is approved by \nADMIN:-"+jwtFilter.getCurrentUser() ,allAdmin);
        // }
        // else
        // {
        //     emailUtils.sendSmpleMessage(jwtFilter.getCurrentUser(),"Account Approved","USER:-"+ user +"\n is disabled by \nADMIN:-"+jwtFilter.getCurrentUser() ,allAdmin);

        // }
    }
    
}
