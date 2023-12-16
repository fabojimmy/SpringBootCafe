package com.inn.cafe.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.inn.cafe.cafe.POJO.User;
import com.inn.cafe.cafe.wrapper.UserWrapper;

import jakarta.transaction.Transactional;


public interface UserDao extends JpaRepository<User,Integer> {
    
    public User findByEmailId(@Param("email") String email);
    public List<UserWrapper> getAllUser();

    @Transactional
    @Modifying
    public Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
    public List<String> getAllAdmin();

    public User findByEmail(@Param("email")String email);
}
