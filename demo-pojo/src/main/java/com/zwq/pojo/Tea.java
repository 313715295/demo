package com.zwq.pojo;

import java.io.Serializable;

/**
 * created by zwq on 2018/5/6
 */
public class Tea implements Serializable{

    private Integer id;
    private String name;
    private Float price;
    private Integer stocks;
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:" + name +
                ", price:" + price +
                ", stocks:" + stocks +
                ", img:" + img +
                '}';
    }
}
