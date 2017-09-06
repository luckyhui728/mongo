package com.mongo.test;

import com.mongo.dao.OrdersDao;
import com.mongo.entity.Item;
import com.mongo.entity.MongoPage;
import com.mongo.entity.Orders;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单测试类
 */
public class OrdersTest {

    private static OrdersDao ordersDao;
    private static String collectionName;

    @BeforeClass
    public static void initSpring() {
        try {
            ApplicationContext app = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
            ordersDao = (OrdersDao) app.getBean("ordersDao");
            collectionName = "orders";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 说明：
     * （1）save ：我们在新增文档时，如果有一个相同_ID的文档时，会覆盖原来的。
     * （2）insert：我们在新增文档时，如果有一个相同的_ID时，就会新增失败。
     * （3）MongoDB提供了insertAll批量添加，可以一次性插入一个列表，效率比较高，save则需要一个一个的插入文档，效率比较低。
     */
    @Test
    public void testSave() throws ParseException { // 测试Save方法添加
        SimpleDateFormat form = new SimpleDateFormat("yyyy-mm-dd");
        // 订单
        Orders order = new Orders();
        order.setOnumber("001");
        order.setDate(form.parse("2015-07-25"));
        order.setCname("zcy");
        // 订单详情
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setPnumber("p001");
        item1.setPrice(4.0);
        item1.setQuantity(5);
        items.add(item1);
        Item item2 = new Item();
        item2.setPnumber("p002");
        item2.setPrice(8.0);
        item2.setQuantity(6);
        items.add(item2);
        order.setItems(items);
        ordersDao.save(order, collectionName);
    }

    @Test
    public void testInsert() throws ParseException { // 测试Insert方法添加
        SimpleDateFormat form = new SimpleDateFormat("yyyy-mm-dd");
        // 订单
        Orders order = new Orders();
        order.setOnumber("001");
        order.setDate(form.parse("2015-07-25"));
        order.setCname("zcy");
        // 订单详情
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setPnumber("p001");
        item1.setPrice(4.0);
        item1.setQuantity(5);
        items.add(item1);
        Item item2 = new Item();
        item2.setPnumber("p002");
        item2.setPrice(8.0);
        item2.setQuantity(6);
        items.add(item2);
        order.setItems(items);
        ordersDao.insert(order, collectionName);
    }

    @Test
    public void testInsertAll() throws ParseException { // 测试InsertAll方法添加
        List<Orders> orders = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            SimpleDateFormat form = new SimpleDateFormat("yyyy-mm-dd");
            // 订单
            Orders order = new Orders();
            order.setOnumber("00" + i);
            order.setDate(form.parse("2015-07-25"));
            order.setCname("zcy" + i);
            // 订单详情
            List<Item> items = new ArrayList<>();
            Item item1 = new Item();
            item1.setPnumber("p00" + i);
            item1.setPrice(4.0 + i);
            item1.setQuantity(5 + i);
            items.add(item1);
            Item item2 = new Item();
            item2.setPnumber("p00" + (i + 1));
            item2.setPrice(8.0 + i);
            item2.setQuantity(6 + i);
            items.add(item2);
            order.setItems(items);
            orders.add(order);
        }
        ordersDao.insertAll(orders);
    }

    /**
     * 说明：
     * （1）remove：删除文档。
     * （2）dropCollection：删除集合。
     */
    @Test
    public void testRemove() throws ParseException {
        ordersDao.remove("onumber", "001", Orders.class, collectionName);
    }

    @Test
    public void testDropCollection() throws ParseException {
        ordersDao.dropCollection("role");
    }

    /**
     * 说明：
     * （1）findOne：返回满足指定查询条件的文档，如果多个文档满足查询，该方法返回第一个文档，根据自然顺序返回文件在磁盘上的顺序,在覆盖的集合中，自然顺序与插入顺序相同。如果没找到对应的文档，会返回null。
     * （2）find：返回满足指定查询条件的所有文档。
     * (3) find(BasicQuery)：自定义查询
     */
    @Test
    public void testFindOne() throws ParseException { // 测试testFindOne方法添加
        Query query = new Query(Criteria.where("onumber").is("002"));
        Orders order = ordersDao.findOne(query, collectionName);
        System.out.println(JSONObject.fromObject(order));
    }

    @Test
    public void testFind() throws ParseException {
        Query query = new Query(Criteria.where("onumber").is("001").and("cname").is("zcy"));
        List<Orders> orders = ordersDao.find(query, collectionName);
        System.out.println("size()==>" + orders.size());
        for (Orders o : orders) {
            System.out.println(JSONArray.fromObject(o));
        }
    }

    @Test
    public void testFind_BasicQuery() throws ParseException {
        BasicDBList basicDBList = new BasicDBList();
        basicDBList.add(new BasicDBObject("onumber", "001"));
        basicDBList.add(new BasicDBObject("cname", "zcy"));
        DBObject obj = new BasicDBObject();
        obj.put("$or", basicDBList);
        Query query = new BasicQuery(obj);
        List<Orders> orders = ordersDao.find(query, collectionName);
        System.out.println("size()==>" + orders.size());
        for (Orders o : orders) {
            System.out.println(JSONArray.fromObject(o));
        }
    }

    @Test
    public void testFind_FieldsQuery() throws ParseException { // find查询时返回指定字段
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.or(new BasicDBObject("onumber", "001"), new BasicDBObject("cname", "zcy"));
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("onumber", 1);
        fieldsObject.put("cname", 1);
        Query query = new BasicQuery(queryBuilder.get(), fieldsObject);
        List<Orders> orders = ordersDao.find(query, collectionName);
        System.out.println("size()==>" + orders.size());
        for (Orders o : orders) {
            System.out.println(JSONArray.fromObject(o));
        }
    }

    @Test
    public void testFindPagination() throws ParseException { // 分页查询
        MongoPage<Orders> page = new MongoPage<>();
        page.setPageNo(2);
        page.setPageSize(3);
        page = ordersDao.selectPagination(page, new BasicDBObject("onumber", "001"), collectionName, "onumber");
        System.out.println("TotalCount：" + page.getRowCount());
        System.out.println("FindCount：" + page.getDatas().size());
        for (Orders o : page.getDatas()) {
            System.out.println(JSONArray.fromObject(o));
        }
    }
}
