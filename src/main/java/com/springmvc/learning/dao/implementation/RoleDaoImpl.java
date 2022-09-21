package com.springmvc.learning.dao.implementation;

import com.springmvc.learning.dao.RoleDao;
import com.springmvc.learning.models.RoleEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository("roleDao")
@Transactional
public class RoleDaoImpl extends GenericDao<RoleEntity> implements RoleDao {
    @Autowired
    SessionFactory sessionFactory;

    public RoleDaoImpl() {
        super(RoleEntity.class);
    }

    @Override
    public Optional<RoleEntity> findByName(String name) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<RoleEntity> criteriaQuery = criteriaBuilder.createQuery(RoleEntity.class);
        Root<RoleEntity> root = criteriaQuery.from(RoleEntity.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("roleName"), name));
        Query<RoleEntity> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        return query.stream().findFirst();
    }
}
