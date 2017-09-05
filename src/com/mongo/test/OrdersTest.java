package com.mongo.test;

import com.mongo.dao.OrdersDao;
import com.mongo.entity.Item;
import com.mongo.entity.Orders;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单测试类
 */
public class OrdersTest {

    private static OrdersDao ordersDao;
    private static ApplicationContext app;
    private static String collectionName;

    @BeforeClass
    public static void initSpring() {
        try {
            app = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
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
        //订单
        Orders order = new Orders();
        order.setOnumber("001");
        order.setDate(form.parse("2015-07-25"));
        order.setCname("zcy");
        //订单详情
        List<Item> items = new ArrayList<Item>();
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
        //订单
        Orders order = new Orders();
        order.setOnumber("001");
        order.setDate(form.parse("2015-07-25"));
        order.setCname("zcy");
        //订单详情
        List<Item> items = new ArrayList<Item>();
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
        List<Orders> orders = new ArrayList<Orders>();
        for (int i = 1; i <= 10; i++) {
            SimpleDateFormat form = new SimpleDateFormat("yyyy-mm-dd");
            //订单
            Orders order = new Orders();
            order.setOnumber("00" + i);
            order.setDate(form.parse("2015-07-25"));
            order.setCname("zcy" + i);
            //订单详情
            List<Item> items = new ArrayList<Item>();
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
        ordersDao.dropCollection(collectionName);
    }


}
