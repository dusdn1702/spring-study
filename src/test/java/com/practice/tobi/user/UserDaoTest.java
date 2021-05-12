package com.practice.tobi.user;


import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.config.InactiveConfigDataAccessException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {
    private UserDao dao;

    @BeforeEach
    private void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ConnectionMaker connectionMaker = new DConnectionMaker();

        this.dao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        User user1 = new User("hi","asdf","sleep");
        User user2 = new User("hello","zxcv","happy");

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User get1 = dao.get(user1.getId());
        assertThat(user1.getName(), is(get1.getName()));
        assertThat(user1.getPassword(), is(get1.getPassword()));

        User get2 = dao.get(user2.getId());
        assertThat(user2.getName(), is(get2.getName()));
        assertThat(user2.getPassword(), is(get2.getPassword()));
    }

    @Test
    void count() throws SQLException, ClassNotFoundException {
        User user1 = new User("hi","asdf","sleep");
        User user2 = new User("hello","zxcv","happy");
        User user3 = new User("bye","qwer","sad");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    @Test
    void getUserFailure() throws SQLException, ClassNotFoundException {
        setUp();

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        assertThatThrownBy(()-> dao.get("nope")).isInstanceOf(IllegalAccessError.class);

    }
}
