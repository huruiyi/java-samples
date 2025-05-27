package vip.fairy.generic.dao;

public interface BaseDao<T> {

  T findById(Long id);

}
