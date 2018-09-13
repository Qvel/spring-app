package com.service.userservice;

import com.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public List<User> getAllUsers() {
        return Stream.of(
                new User.UserBuilder().setAge(23L).setName("Serhii").build(),
                new User.UserBuilder().setAge(23L).setName("Vlad").build(),
                new User.UserBuilder().setAge(24L).setName("Max").build(),
                new User.UserBuilder().setAge(24L).setName("Andrei").build()
        ).collect(Collectors.toList());
    }
}
