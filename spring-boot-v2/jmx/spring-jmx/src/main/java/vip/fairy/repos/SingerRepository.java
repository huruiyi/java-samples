package vip.fairy.repos;


import org.springframework.data.repository.CrudRepository;
import vip.fairy.entities.Singer;

import java.util.List;

public interface SingerRepository extends CrudRepository<Singer, Long> {

	List<Singer> findByFirstName(String firstName);
}
