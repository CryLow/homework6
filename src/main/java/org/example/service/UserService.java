package org.example.service;

import org.example.annotation.Transaction;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transaction
    public void examTransaction(User user){
        try {
            userRepository.save(user);
        }   catch (Exception e){
            throw e;
        }
    }

    public User findById(long id){
        return userRepository.findById(id);
    }
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
    public void createUser(User user){
        userRepository.save(user);
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }
    public void updateUser(User user){
        userRepository.update(user);
    }
}
