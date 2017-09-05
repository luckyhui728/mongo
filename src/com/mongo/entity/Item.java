package com.mongo.entity;

/**
 * Created by Mr.Z on 2017/9/5.
 */
public class Item {

    //数量
    private Integer quantity;
    //单价
    private Double price;
    //产品编码
    private String pnumber;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }
}
