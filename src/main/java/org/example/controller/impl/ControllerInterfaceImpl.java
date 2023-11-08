package org.example.controller.impl;

import org.example.controller.ControllerInterface;
import org.example.model.User;
import org.example.service.UserService;
import org.example.utilites.ConnectionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class ControllerInterfaceImpl implements ControllerInterface {
    private final ConnectionHolder connectionHolder;
    private final UserService userService;

    @Autowired
    public ControllerInterfaceImpl(ConnectionHolder connectionHolder, UserService userService) {
        this.connectionHolder = connectionHolder;
        this.userService = userService;
    }

    @Override
    public void start() {
        Connection connection = null;

        try {
            connection = connectionHolder.getConnection();

            User user = new User();
            user.setId(0);
            user.setUsername("Test1");
            user.setPassword("password");
            user.setFirst_name("Joey");
            user.setSecond_name("Tribbiani");
            user.setRole(0);

            userService.examTransaction(user);
        } catch (Exception e){
            throw e;
        }
    }
}
