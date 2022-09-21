package com.springmvc.learning.service;

import com.springmvc.learning.dao.service.RoleService;
import com.springmvc.learning.models.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class RoleConverter implements Converter<String, RoleEntity> {
    @Autowired
    RoleService roleService;

    @Override
    public RoleEntity convert(String role) {
        return roleService.findByName(role).orElseThrow();
    }
}
