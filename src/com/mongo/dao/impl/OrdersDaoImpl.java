package com.mongo.dao.impl;

import com.mongo.dao.OrdersDao;
import com.mongo.entity.Orders;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("ordersDao")
public class OrdersDaoImpl implements OrdersDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(Orders object, String collectionName) {
        mongoTemplate.insert(object, collectionName);
    }

    @Override
    public void save(Orders object, String collectionName) {
        mongoTemplate.save(object, collectionName);
    }

    @Override
    public void insertAll(List<Orders> objects) {
        mongoTemplate.insertAll(objects);
    }

    @Override
    public void remove(String field, String value, Class cls, String collectionName) {
        mongoTemplate.findAndRemove(new Query(Criteria.where(field).is(value)), cls, collectionName);
    }

    @Override
    public void remove(String field, String value, String collectionName) {
        mongoTemplate.remove(new Query(Criteria.where(field).is(value)), collectionName);
    }

    @Override
    public void remove(String field, String value, Class cls) {
        mongoTemplate.remove(new Query(Criteria.where(field).is(value)), cls);
    }

    @Override
    public void dropCollection(String collectionName) {
        mongoTemplate.dropCollection(collectionName);
    }

    @Override
    public void dropCollection(Class cls) {
        mongoTemplate.dropCollection(cls);
    }

    @Override
    public Orders findOne(Query query, Class cls, String collectionName) {
        return mongoTemplate.findOne(query, Orders.class, collectionName);
    }


}
