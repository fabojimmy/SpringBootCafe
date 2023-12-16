package com.inn.cafe.cafe.wrapper;

import lombok.Data;

@Data

public class ProductWrapper {

    private Integer id;
    private String name;
    private Integer categoryid;
    private String categoryName;
    private String description;
    private Integer price;
    private String status;

    public ProductWrapper(){}   

    public ProductWrapper(Integer id, String name, Integer categoryId, String categoryName, String description, Integer price, String status) {
        this.id = id;
        this.name=name;
        this.categoryid=categoryId;
        this.categoryName=categoryName;
        this.description=description;
        this.price=price;
        this.status=status;
    }
}
