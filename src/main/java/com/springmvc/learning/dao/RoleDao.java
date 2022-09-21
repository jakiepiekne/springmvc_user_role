package com.springmvc.learning.dao;

import com.springmvc.learning.models.RoleEntity;

import java.util.Optional;

public interface RoleDao extends Dao<RoleEntity> {
    Optional<RoleEntity> findByName(String name);
}
