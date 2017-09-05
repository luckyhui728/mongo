package com.mongo.common;

import java.util.List;

public interface MongoBase<T> {

    /**=================================================================================================================*/

    /**
     * 添加，如果ObjectId存在则报错
     *
     * @param object         添加的对象
     * @param collectionName 集合名
     */
    void insert(T object, String collectionName);

    /**
     * 添加，如果ObjectId存在则覆盖
     *
     * @param object         添加的对象
     * @param collectionName 集合名
     */
    void save(T object, String collectionName);

    /**
     * 批量添加
     *
     * @param object 对象列表
     */
    void insertAll(List<T> object);

    /**=================================================================================================================*/

    /**
     * 根据条件删除
     *
     * @param field          查询-键
     * @param value          查询-值
     * @param cls            类型
     * @param collectionName 集合名
     */
    void remove(String field, String value, Class cls, String collectionName);

    /**
     * 根据条件删除
     *
     * @param field          查询-键
     * @param value          查询-值
     * @param collectionName 集合名
     */
    void remove(String field, String value, String collectionName);

    /**
     * 根据条件删除
     *
     * @param field 查询-键
     * @param value 查询-值
     * @param cls   类型
     */
    void remove(String field, String value, Class cls);

    /**
     * 删除集合
     *
     * @param collectionName 集合名
     */
    void dropCollection(String collectionName);

    /**
     * 删除集合
     *
     * @param cls 集合类型
     */
    void dropCollection(Class cls);

    /**=================================================================================================================*/

}
