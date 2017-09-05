package com.mongo.dao;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * 封装Dao接口
 */
public interface RepositoryDao {

    List<T> findAll(Class<T> cls);

    Object findById(Class<T> cls, String id);

    void insert(Object object);

    void deleteById(Class<T> cls, String tid);
}
