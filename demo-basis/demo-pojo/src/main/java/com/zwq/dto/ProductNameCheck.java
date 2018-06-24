package com.zwq.dto;

import java.io.Serializable;

/**
 * created by zwq on 2018/5/30
 */
public class ProductNameCheck implements Serializable {
    private String productName;
    private Integer productId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductNameCheck{" +
                "productName='" + productName + '\'' +
                ", productId=" + productId +
                '}';
    }
}
