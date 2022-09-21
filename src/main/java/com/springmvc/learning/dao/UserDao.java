package com.springmvc.learning.dao;

import com.springmvc.learning.models.UserEntity;

import java.util.Optional;

public interface UserDao extends Dao<UserEntity> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}
