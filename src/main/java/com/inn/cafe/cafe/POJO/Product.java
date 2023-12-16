package com.inn.cafe.cafe.POJO;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@NamedQuery(name = "Product.allProducts",query = "SELECT new com.inn.cafe.cafe.wrapper.ProductWrapper(p.id,p.name,p.category.id,p.category.name,p.description,p.price,p.status) FROM Product p")

@NamedQuery(name = "Product.updateStatus",query = "UPDATE Product p SET p.status=:status WHERE p.id=:id")

@NamedQuery(name = "Product.getByCategory",query = "SELECT new com.inn.cafe.cafe.wrapper.ProductWrapper(p.id,p.name,p.category.id,p.category.name,p.description,p.price,p.status) FROM Product p WHERE p.category.id=:id")

@NamedQuery(name = "Product.getByProductId",query = "SELECT new com.inn.cafe.cafe.wrapper.ProductWrapper(p.id,p.name,p.category.id,p.category.name,p.description,p.price,p.status) FROM Product p WHERE p.id=:id")

@Data
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_fk",nullable = false)
    private Category category;

    private String description;

    private Integer price;
    private String status;
}
