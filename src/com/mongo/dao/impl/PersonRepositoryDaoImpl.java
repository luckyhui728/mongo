package com.mongo.dao.impl;

import com.mongo.dao.RepositoryDao;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * 实现Dao接口
 */
public class PersonRepositoryDaoImpl implements RepositoryDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 通用查询,传入"实体.Class"
     */
    public List<T> findAll(Class<T> cls) {
        return getMongoTemplate().find(new Query(), cls);
    }

    /**
     * 通用ID查询,传入"实体.Class和ID"
     */
    public Object findById(Class<T> cls, String id) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        return getMongoTemplate().findById(id, cls);
    }

    /**
     * 通用插入数据,传入实体类
     */
    public void insert(Object object) {
        getMongoTemplate().insert(object);
    }

    /**
     * 通用删除,传入实体和Id
     */
    public void deleteById(Class<T> cls, String tid) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(tid));
        getMongoTemplate().remove(query, cls);
    }
}
