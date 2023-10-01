package ru.kata.spring.boot_security.demo.configs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositiories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found by %s" + userId));
    }

    @Transactional
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void removeUser(Integer userId) {
        userRepository.delete(userRepository.getById(userId));
    }

    @Transactional
    @Override
    public User updateUserById(Integer userId, User user) {
        User userToUpdate  = getUserById(userId);
        userToUpdate .setName(user.getName());
        userToUpdate .setSurname(user.getSurname());
        userToUpdate .setAge(user.getAge());
        userToUpdate .setEmail(user.getEmail());
        userToUpdate .setPassword(user.getPassword());
        userToUpdate .setRoles(user.getRoles());
        return userRepository.save(userToUpdate );
    }
}
