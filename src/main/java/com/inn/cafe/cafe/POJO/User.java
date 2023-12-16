package com.inn.cafe.cafe.POJO;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@NamedQuery(name="User.findByEmailId",query="select u from User u where u.Email=:email")

@NamedQuery(name="User.getAllUser",query="select new com.inn.cafe.cafe.wrapper.UserWrapper(u.id,u.name,u.Email,u.contactNumber,u.status) from User u Where u.role='user'")

@NamedQuery(name = "User.updateStatus",query = "UPDATE User u SET u.status=:status WHERE u.id=:id")

@NamedQuery(name="User.getAllAdmin",query="select u.Email from User u Where u.role='admin'")

@NamedQuery(name="User.findByEmail",query="select u from User u Where u.Email=:email")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user")
public class User implements Serializable{
    
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Integer id;

@Column(name="name")
private String name;

@Column(name = "contactNumber")
private String contactNumber;

@Column(name="email")
private String Email;

@Column(name = "password")
private String password;

@Column(name = "status")
private String status;

@Column(name = "role")
private String role;

//    public User(){}
// public User(String name, String contactNumber, String email, String password, String status, String role) {
//     this.name = name;
//     this.contactNumber = contactNumber;
//     this.email = email;
//     this.password = password;
//     this.status = status;
//     this.role = role;
// }
 
    // /**
    //  * @return Integer return the id
    //  */
    // public Integer getId() {
    //     return id;
    // }

    // /**
    //  * @param id the id to set
    //  */
    // public void setId(Integer id) {
    //     this.id = id;
    // }

    // /**
    //  * @return String return the name
    //  */
    // public String getName() {
    //     return name;
    // }

    // /**
    //  * @param name the name to set
    //  */
    // public void setName(String name) {
    //     this.name = name;
    // }

    // /**
    //  * @return String return the contactNumber
    //  */
    // public String getContactNumber() {
    //     return contactNumber;
    // }

    // /**
    //  * @param contactNumber the contactNumber to set
    //  */
    // public void setContactNumber(String contactNumber) {
    //     this.contactNumber = contactNumber;
    // }

    // /**
    //  * @return String return the email
    //  */
    // public String getEmail() {
    //     return email;
    // }

    // /**
    //  * @param email the email to set
    //  */
    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // /**
    //  * @return String return the password
    //  */
    // public String getPassword() {
    //     return password;
    // }

    // /**
    //  * @param password the password to set
    //  */
    // public void setPassword(String password) {
    //     this.password = password;
    // }

    // /**
    //  * @return String return the status
    //  */
    // public String getStatus() {
    //     return status;
    // }

    // /**
    //  * @param status the status to set
    //  */
    // public void setStatus(String status) {
    //     this.status = status;
    // }

    // /**
    //  * @return String return the role
    //  */
    // public String getRole() {
    //     return role;
    // }

    // /**
    //  * @param role the role to set
    //  */
    // public void setRole(String role) {
    //     this.role = role;
    // }

}

