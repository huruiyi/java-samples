package com.jcg.jpa.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPADemo {

  private static final EntityManagerFactory emFactoryObj;
  private static final String PERSISTENCE_UNIT_NAME = "JPADemo";

  static {
    emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
  }

  // This Method Is Used To Retrieve The 'EntityManager' Object
  public static EntityManager getEntityManager() {
    return emFactoryObj.createEntityManager();
  }

  public static void main(String[] args) {
    EntityManager entityMgr = getEntityManager();
    entityMgr.getTransaction().begin();

    Farmer farmObj = new Farmer();
    farmObj.setId(111);
    farmObj.setName("Harry Potter");
    farmObj.setVillage("Scottish Highlands");
    entityMgr.persist(farmObj);

    entityMgr.getTransaction().commit();

    entityMgr.clear();
    System.out.println("Record Successfully Inserted In The Database");
  }
}
