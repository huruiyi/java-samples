package com.example.demo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public class ExtendedRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ExtendedRepository<T, ID> {

  private EntityManager entityManager;

  public ExtendedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }

  @Transactional
  public List<T> findByAttributeContainsText(String attributeName, String text) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cQuery = builder.createQuery(getDomainClass());
    Root<T> root = cQuery.from(getDomainClass());
    cQuery.select(root).where(builder.like(root.<String>get(attributeName), "%" + text + "%"));
    TypedQuery<T> query = entityManager.createQuery(cQuery);
    return query.getResultList();
  }

  @Transactional
  public T updateWith(T with, ID id) {
    T persisted = entityManager.find(this.getDomainClass(), id);

    if (persisted != null) {
      final BeanWrapper sourceBean = new BeanWrapperImpl(with);
      final BeanWrapper destBean = new BeanWrapperImpl(persisted);
      final PropertyDescriptor[] propertyDescriptors = sourceBean.getPropertyDescriptors();
      for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {
        Object pv = sourceBean.getPropertyValue(propertyDescriptor.getName());
        if (pv != null && destBean.isWritableProperty(propertyDescriptor.getName())) {
          destBean.setPropertyValue(propertyDescriptor.getName(), pv);
        }
      }
      entityManager.persist(persisted);
    }
    return persisted;
  }

}
