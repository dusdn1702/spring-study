package com.practice.tobi.user;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {
    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
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

        assertThat(user2.getName(), is(user.getName()));
    }
}
