package com.practice.tobi.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
