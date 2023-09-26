package ru.kata.spring.boot_security.demo.configs.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
public interface DaoService {

    List<User> getAllUsers();

    User getUserById(Long userId);

    void addUser(User user);

    void removeUser(Long userId);

    void updateUser(Long userId, User user);
}
