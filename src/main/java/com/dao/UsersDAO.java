package com.dao;

import com.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDAO extends CrudRepository<User,Long> {
    List<User> findByNameIgnoreCaseContaining(String name);
}
