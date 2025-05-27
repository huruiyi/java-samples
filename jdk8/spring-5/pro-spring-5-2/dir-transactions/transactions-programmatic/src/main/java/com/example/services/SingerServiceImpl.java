package com.example.services;

import com.example.entities.Singer;
import com.example.repos.SingerRepository;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service("singerService")
@Repository
public class SingerServiceImpl implements SingerService {

  @Autowired
  private SingerRepository singerRepository;
  @Autowired
  private TransactionTemplate transactionTemplate;
  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Singer> findAll() {
    return Lists.newArrayList(singerRepository.findAll());
  }

  /**
   * API  changed in  2.0.0.M3 findOne became findById
   *
   * @param id
   * @return
   */
  @Override
  public Singer findById(Long id) {
    return singerRepository.findById(id).get();
  }

  @Override
  public Singer save(Singer singer) {
    return singerRepository.save(singer);
  }

  @Override
  public long countAll() {
    return transactionTemplate.execute(transactionStatus -> em.createNamedQuery(Singer.COUNT_ALL,
        Long.class).getSingleResult());
  }
}
