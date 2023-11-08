package org.example.repository;

import org.example.model.User;

import java.util.List;

public interface UserRepository {

    User findById(long id);
    User findByUsername(String username);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(User user);
}
