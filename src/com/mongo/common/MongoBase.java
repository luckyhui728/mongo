package com.mongo.common;

import com.mongo.entity.MongoPage;
import com.mongo.entity.Orders;
import com.mongodb.DBObject;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Set;

public interface MongoBase<T> {

    /**
     * 查询所有集合名称
     * @return
     */
    Set<String> getCollections();

    /**
     * 添加，如果ObjectId存在则覆盖
     *
     * @param object         对象
     * @param collectionName 集合名称
     */
    void save(T object, String collectionName);

    /**
     * 添加，如果ObjectId存在则报错
     *
     * @param object         添加的对象
     * @param collectionName 集合名称
     */
    void insert(T object, String collectionName);

    /**
     * 批量添加，性能好
     *
     * @param object 对象列表
     */
    void insertAll(List<T> object);


    /**
     * 根据字段、集合类型、集合名称删除记录
     *
     * @param field          键
     * @param value          值
     * @param cls            类型
     * @param collectionName 集合名称
     */
    void remove(String field, String value, Class cls, String collectionName);

    /**
     * 根据字段、集合名称删除记录
     *
     * @param field          键
     * @param value          值
     * @param collectionName 集合名称
     */
    void remove(String field, String value, String collectionName);

    /**
     * 根据字段、集合类型删除记录
     *
     * @param field 键
     * @param value 值
     * @param cls   类型
     */
    void remove(String field, String value, Class cls);

    /**
     * 根据集合名称删除集合
     *
     * @param collectionName 集合名称
     */
    void dropCollection(String collectionName);

    /**
     * 根据集合类型删除集合
     *
     * @param cls 集合类型
     */
    void dropCollection(Class cls);


    /**
     * （Query查询）根据条件查询，如果有多条记录只返回第一条（插入顺序）
     *
     * @param query          查询条件
     * @param collectionName 集合名称
     * @return 查询结果
     */
    T findOne(Query query, String collectionName);

    /**
     * （Query查询）根据条件查询，如果有多条记录则全部返回
     *
     * @param query          查询条件
     * @param collectionName 集合名称
     * @return 查询结果
     */
    List<T> find(Query query, String collectionName);

    /**
     * （BasicQuery查询）可以自定义查询，查询时可以指定返回的需要的字段
     * 说明：
     * 1 或者true表示返回字段，0 或者false表示不返回该字段。
     * _id:默认就是1，没指定返回该字段时，默认会返回，除非设置为0是，就不会返回该字段。
     * 指定返回字段，当文档字段多并数据大时，指定返回我们需要的字段，这样既节省传输数据量，减少了内存消耗，提高了性能，在数据大时，性能很明显的。
     * DEMO：
     * QueryBuilder queryBuilder = new QueryBuilder();
     * queryBuilder.or(new BasicDBObject("onumber", "002"), new BasicDBObject("cname","zcy1"));
     * BasicDBObject fieldsObject=new BasicDBObject();
     * fieldsObject.put("onumber", 1);
     * fieldsObject.put("cname", 1);
     * uery query = new BasicQuery(queryBuilder.get(),fieldsObject);
     *
     * @param query          查询条件
     * @param collectionName 集合名称
     * @return 查询结果
     */
    List<T> basicQuery(BasicQuery query, String collectionName);


    /**
     * 根据查询条件修改数据，如果目标字段不存在则创建
     *
     * @param query          查询条件
     * @param update         修改属性
     * @param collectionName 集合名称
     */
    void update(Query query, Update update, String collectionName);

    /**
     * 根据查询条件修改数据，如果目标字段不存在则创建
     *
     * @param query  查询条件
     * @param update 修改属性
     * @param cls    类型
     */
    void update(Query query, Update update, Class cls);

    /**
     * 根据查询条件修改数据，如果存在多条记录，则只修改第一条
     *
     * @param query          查询条件
     * @param update         修改属性
     * @param collectionName 集合名称
     */
    void updateFirst(Query query, Update update, String collectionName);

    /**
     * 根据查询条件修改数据，如果存在多条记录，则只修改第一条
     *
     * @param query  查询条件
     * @param update 修改属性
     * @param cls    类型
     */
    void updateFirst(Query query, Update update, Class cls);

    /**
     * 根据查询条件修改数据，如果存在多条记录，则全部修改
     *
     * @param query          查询条件
     * @param update         修改属性
     * @param collectionName 集合名称
     */
    void updateMulti(Query query, Update update, String collectionName);

    /**
     * 根据查询条件修改数据，如果存在多条记录，则全部修改
     *
     * @param query  查询条件
     * @param update 修改属性
     * @param cls    类型
     */
    void updateMulti(Query query, Update update, Class cls);

    /**
     * （BasicUpdate）可以自定义修改，可以同时对多个字段进行修改
     * 说明：
     * BasicUpdate JSON格式，需要自己实现update SQL，BasicUpdate需要手动实现$set等操作符SQL语句，
     * 也可以使用Update的一些操作修改文档的操作方法，因为继承了Update类
     * DEMO：
     * BasicDBObject basicDBObject = new BasicDBObject();
     * basicDBObject.put("$set", new BasicDBObject("date","2015-08-09"));
     * Update update = new BasicUpdate(basicDBObject);
     * mongoTemplate.updateFirst(new Query(Criteria.where("cname").is("zcy")), update,collectionName);
     *
     * @param query          查询条件
     * @param update         修改属性
     * @param collectionName 集合名称
     */
    void basicUpdate(Query query, BasicUpdate update, String collectionName);


    /**
     * 分页查询
     * 模糊搜索：query.addCriteria(Criteria.where(condition.getKey()).regex(".*?\\" +condition.getValue().toString()+ ".*"));
     * 去除重复：distinct()
     *
     * @param page           分页参数
     * @param query          查询条件
     * @param collectionName 集合名称
     * @return 结果集
     */
    MongoPage<Orders> selectPagination(MongoPage<Orders> page, DBObject query, String collectionName, String sortNam, Sort.Direction direction);
}
