package com.practice.tobi;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.practice.tobi.user.User;
import com.practice.tobi.user.UserDao;

@SpringBootApplication
public class TobiApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        SpringApplication.run(TobiApplication.class, args);
    }

}
