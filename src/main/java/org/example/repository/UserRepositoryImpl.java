package org.example.repository;

import org.example.mapper.UserMapper;
import org.example.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component

public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public User findById(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,new UserMapper(),new Object[]{id});
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql,new UserMapper(), new Object[]{username});
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql,new UserMapper());
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (username, password, first_name, second_name, role) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getFirst_name(),user.getSecond_name(),user.getRole());
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, first_name = ?, second_name = ?, role = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getFirst_name(), user.getSecond_name(), user.getRole(), user.getId());
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql,user.getId());
    }
}
