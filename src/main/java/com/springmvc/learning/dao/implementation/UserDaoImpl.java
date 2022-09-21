package com.springmvc.learning.dao.implementation;

import com.springmvc.learning.dao.UserDao;
import com.springmvc.learning.models.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends GenericDao<UserEntity> implements UserDao {
    @Autowired
    SessionFactory sessionFactory;

    public UserDaoImpl() {
        super(UserEntity.class);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
        Query<UserEntity> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        return query.stream().findFirst();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email));
        Query<UserEntity> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        return query.stream().findFirst();
    }
}
