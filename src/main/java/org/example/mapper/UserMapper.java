package org.example.mapper;

import org.example.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setFirst_name(resultSet.getString("first_name"));
        user.setSecond_name(resultSet.getString("second_name"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getInt("role"));
        return user;
    }
}
