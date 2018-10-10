package com.service.userservice;

import com.dao.UsersDAO;
import com.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for {@link User} entity
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserServiceImpl implements UserService{

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private final UsersDAO usersDAO;
    private User user;

    @Autowired
    public UserServiceImpl(UsersDAO usersDAO,User user) {
        this.usersDAO = usersDAO;
        this.user = user;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        LOG.debug("UserServiceImpl.findAll input = {}");
        List<User> users = new ArrayList<>();
        usersDAO.findAll().forEach(
                users::add
        );
        LOG.debug("Users" + users);
        return users;
    }

    @Override
    @Transactional
    public List<User> findByNameIgnoreCaseContaining(String name) {
        return usersDAO.findByNameIgnoreCaseContaining(name);
    }

    @Override
    @Transactional
    public User findById(Long id) {
        LOG.debug("UserServiceImpl.findById input = " + id);
        user = usersDAO.findById(id).get();
        LOG.debug("UserServiceImpl.findById result = " + user);
        return user;
    }

    @Override
    @Transactional
    public User save(User user) {
        LOG.debug("UserServiceImpl.save input = " + user);
        return usersDAO.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        LOG.debug("UserServiceImpl.deleteUserById input = " + id);
        usersDAO.deleteById(id);
    }
}
