package vip.fairy.generic.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import vip.fairy.generic.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {

  private final Class<T> currentClass;

  public BaseDaoImpl() {
    Type type = this.getClass().getGenericSuperclass();
    Type[] types = ((ParameterizedType) type).getActualTypeArguments();
    currentClass = (Class<T>) types[0];
  }

  @Override
  public T findById(Long id) {
    System.out.println(currentClass.getName());
    return null;
  }
  
}
