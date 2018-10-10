package com.service.userservice;

import com.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    List<User> findByNameIgnoreCaseContaining(String name);
    User findById(Long id);
    User save(User user);
    void deleteUserById(Long id);
}
