package com.example.repos;

import com.example.entities.Singer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository extends CrudRepository<Singer, Long> {

	List<Singer> findByFirstName(String firstName);

}
