package com.practice.tobi.user;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ConnectionMaker connectionMaker = new DConnectionMaker();

        UserDao userDao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("sally");
        user.setName("yeonwoo");
        user.setPassword("hi");

        userDao.add(user);

        System.out.println(user.getId() + " 추가");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회");
    }
}
