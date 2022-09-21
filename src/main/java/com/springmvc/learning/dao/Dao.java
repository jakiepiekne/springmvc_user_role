package com.springmvc.learning.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    void create(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(Long id);

    Optional<T> findById(Long id);

    List<T> findAll();

}
