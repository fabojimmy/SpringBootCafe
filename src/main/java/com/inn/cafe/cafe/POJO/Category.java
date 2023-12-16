package com.inn.cafe.cafe.POJO;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;


@NamedQuery(name="Category.getAllCategory",query="select c from Category c Where id in (select p.category from Product p Where p.status ='true')")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "category")
public class Category implements Serializable{
    
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    
}
