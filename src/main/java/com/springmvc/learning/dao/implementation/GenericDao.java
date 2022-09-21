package com.springmvc.learning.dao.implementation;

import com.springmvc.learning.dao.Dao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class GenericDao<T> implements Dao<T> {
    private final Class<T> type;

    @Autowired
    SessionFactory sessionFactory;

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);

    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaDelete<T> criteriaQuery = criteriaBuilder.createCriteriaDelete(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
        sessionFactory.getCurrentSession().createQuery(criteriaQuery).executeUpdate();
    }

    @Override
    public Optional<T> findById(Long id) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
        Query<T> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        return query.stream().findFirst();
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);
        Query<T> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }
}
