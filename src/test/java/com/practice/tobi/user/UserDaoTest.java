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

        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        User user = new User("hi", "je;;", "wer");

        userDao.add(user);
        assertThat(userDao.getCount(), is(1));

        User user2 = userDao.get(user.getId());

        assertThat(user2.getName(), is(user.getName()));
    }

    @Test
    void count() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ConnectionMaker connectionMaker = new DConnectionMaker();

        UserDao userDao = context.getBean("userDao", UserDao.class);
        User user1 = new User("hi","asdf","sleep");
        User user2 = new User("hello","zxcv","happy");
        User user3 = new User("bye","qwer","sad");

        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        userDao.add(user1);
        assertThat(userDao.getCount(), is(1));

        userDao.add(user2);
        assertThat(userDao.getCount(), is(2));

        userDao.add(user3);
        assertThat(userDao.getCount(), is(3));
    }
}
