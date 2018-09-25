package com.dao;

import com.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersDAO extends CrudRepository<User,Long> {

    List<User> findByNameIgnoreCaseContaining(String name);

}
