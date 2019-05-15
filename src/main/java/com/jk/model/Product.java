package com.jk.model;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 3549537193915083636L;
    private String id;

    private Double product_price;

    private String product_sell_point;

    private String product_title;

    private int product_num;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_sell_point() {
        return product_sell_point;
    }

    public void setProduct_sell_point(String product_sell_point) {
        this.product_sell_point = product_sell_point;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public int getProduct_num() {
        return product_num;
    }

    public void setProduct_num(int product_num) {
        this.product_num = product_num;
    }
}
