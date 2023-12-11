package com.apress.prospring5.ch9.repos;

import com.apress.prospring5.ch9.entities.Singer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends CrudRepository<Singer, Long> {

}
