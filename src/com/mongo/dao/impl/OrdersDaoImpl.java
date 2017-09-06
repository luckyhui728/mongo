package com.mongo.dao.impl;

import com.mongo.dao.OrdersDao;
import com.mongo.entity.MongoPage;
import com.mongo.entity.Orders;
import com.mongodb.DBObject;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("ordersDao")
public class OrdersDaoImpl implements OrdersDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Orders object, String collectionName) {
        mongoTemplate.save(object, collectionName);
    }

    @Override
    public void insert(Orders object, String collectionName) {
        mongoTemplate.insert(object, collectionName);
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
    public Orders findOne(Query query, String collectionName) {
        return mongoTemplate.findOne(query, Orders.class, collectionName);
    }

    @Override
    public List<Orders> find(Query query, String collectionName) {
        return mongoTemplate.find(query, Orders.class, collectionName);
    }

    @Override
    public List<Orders> basicQuery(BasicQuery query, String collectionName) {
        return mongoTemplate.find(query, Orders.class, collectionName);
    }

    @Override
    public void update(Query query, Update update, String collectionName) {
        mongoTemplate.upsert(query, update, collectionName);
    }

    @Override
    public void update(Query query, Update update, Class cls) {
        mongoTemplate.upsert(query, update, cls);
    }

    @Override
    public void updateFirst(Query query, Update update, String collectionName) {
        mongoTemplate.updateFirst(query, update, collectionName);
    }

    @Override
    public void updateFirst(Query query, Update update, Class cls) {
        mongoTemplate.updateFirst(query, update, cls);
    }

    @Override
    public void updateMulti(Query query, Update update, String collectionName) {
        mongoTemplate.updateMulti(query, update, collectionName);
    }

    @Override
    public void updateMulti(Query query, Update update, Class cls) {
        mongoTemplate.updateMulti(query, update, cls);
    }

    @Override
    public void basicUpdate(Query query, BasicUpdate update, String collectionName) {
        throw new RuntimeException("可自行实现...");
    }

    @Override
    public MongoPage<Orders> selectPagination(MongoPage<Orders> page, DBObject query, String collectionName, String sortName) {
        Query basicQuery = new BasicQuery(query);
        // 查询总数
        int count = (int) mongoTemplate.count(basicQuery, Orders.class);
        page.setRowCount(count);
        // 排序
        basicQuery.with(new Sort(Sort.Direction.ASC, sortName));
        basicQuery.limit(page.getPageSize());
        basicQuery.skip(page.getSkip());
        List<Orders> datas = mongoTemplate.find(basicQuery, Orders.class);
        page.setDatas(datas);
        return page;
    }
}
