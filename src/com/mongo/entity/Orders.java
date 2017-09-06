package com.mongo.entity;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
public class Orders {

    // ID
    private String id;
    // 订单号
    private String onumber;
    // 日期
    private Date date;
    // 客户名称
    private String cname;
    // 订单
    private List<Item> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOnumber() {
        return onumber;
    }

    public void setOnumber(String onumber) {
        this.onumber = onumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
