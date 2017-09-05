package com.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 数据库的实体
 */
@Document(collection = "role") // 集合名字
public class Role {

    private String id;
    private String username;
    private String role;
    private String beizhu;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", beizhu='" + beizhu + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}
