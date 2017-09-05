package com.mongo.test;

import com.mongo.dao.impl.PersonRepositoryDaoImpl;
import com.mongo.entity.Role;
import com.mongo.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserTest {

    /**
     * 测试
     */
    public static void main(String[] args) {
        ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
        Class u = User.class;
        Class r = Role.class;

        PersonRepositoryDaoImpl personRepository = (PersonRepositoryDaoImpl) ac.getBean("personRepository");

        /**
         * User添加数据
         */
        // User user = new User();
        // user.setId(new ObjectId().toString());
        // user.setAge("88");
        // user.setPassword("123456");
        // user.setUsername("jack");
        // personRepository.insert(user);

        /**
         * role添加
         */
        // Role role = new Role();
        // role.setId(new ObjectId().toString());
        // role.setBeizhu("****");
        // role.setRole("@@@@");
        // role.setUsername("jack");
        // personRepository.insert(role);

        /**
         * 查询所有数据
         */
        System.out.println(personRepository.findAll(u));
        System.out.println(personRepository.findAll(r));

        /**
         * 根据Id查询
         */
        // User u = (User) personRepository.findById(u, "53a1514782946cae7641d83d");
        // System.out.println(u.getUsername());

        /**
         * 根据Id删除
         */
        // String objectId="59ae0a23c9f703532716d741";
        // personRepository.deleteById(r, objectId);
    }
}
