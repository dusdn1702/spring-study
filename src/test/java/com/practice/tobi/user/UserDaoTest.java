package com.practice.tobi.user;


import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.config.InactiveConfigDataAccessException;
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

        User user1 = new User("hi","asdf","sleep");
        User user2 = new User("hello","zxcv","happy");

        userDao.add(user1);
        userDao.add(user2);
        assertThat(userDao.getCount(), is(2));

        User get1 = userDao.get(user1.getId());
        assertThat(user1.getName(), is(get1.getName()));
        assertThat(user1.getPassword(), is(get1.getPassword()));

        User get2 = userDao.get(user2.getId());
        assertThat(user2.getName(), is(get2.getName()));
        assertThat(user2.getPassword(), is(get2.getPassword()));
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

    @Test
    void getUserFailure() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ConnectionMaker connectionMaker = new DConnectionMaker();

        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        assertThatThrownBy(()-> userDao.get("nope")).isInstanceOf(IllegalAccessError.class);

    }
}
