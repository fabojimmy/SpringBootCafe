package com.inn.cafe.cafe.jwt;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inn.cafe.cafe.POJO.User;
import com.inn.cafe.cafe.dao.UserDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerUsersDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    private User user;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername{}", username);
         user=userDao.findByEmailId(username);
        if(!Objects.isNull(user))
        {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
        }
        throw new UsernameNotFoundException("Unimplemented method 'loadUserByUsername'");
    }
    
    public User getUserDetail(){

        return user;
    }
}
