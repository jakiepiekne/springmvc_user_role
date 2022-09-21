package com.springmvc.learning.dao.service;

import com.springmvc.learning.dao.UserDao;
import com.springmvc.learning.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public void create(UserEntity entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userDao.create(entity);
    }

    public void update(UserEntity entity) {
        if (entity.getPassword().isEmpty()) {
            userDao.findById(entity.getId())
                    .ifPresent(user -> entity.setPassword(user.getPassword()));
        } else {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        userDao.update(entity);
    }

    public void delete(UserEntity entity) {
        userDao.delete(entity);
    }

    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    public Optional<UserEntity> findById(Long id) {
        return userDao.findById(id);
    }

    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Optional<UserEntity> findByEmailOrUsername(String data) {
        Optional<UserEntity> userByEmail = userDao.findByEmail(data);
        Optional<UserEntity> userByUsername = userDao.findByUsername(data);
        return Stream.of(userByUsername, userByEmail).filter(Optional::isPresent).findFirst().get();
    }
}
