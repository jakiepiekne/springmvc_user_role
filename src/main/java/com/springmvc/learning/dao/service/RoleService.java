package com.springmvc.learning.dao.service;

import com.springmvc.learning.dao.RoleDao;
import com.springmvc.learning.models.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public void create(RoleEntity entity) {
        roleDao.create(entity);
    }

    public void update(RoleEntity entity) {
        roleDao.update(entity);
    }

    public void delete(RoleEntity entity) {
        roleDao.delete(entity);
    }

    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }

    public Optional<RoleEntity> findById(Long id) {
        return roleDao.findById(id);
    }

    public Optional<RoleEntity> findByName(String name) {
        return roleDao.findByName(name);
    }

    public List<RoleEntity> findAll() {
        return roleDao.findAll();

    }

}
