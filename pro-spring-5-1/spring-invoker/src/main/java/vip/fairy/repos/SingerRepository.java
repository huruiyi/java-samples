package vip.fairy.repos;

import vip.fairy.entities.Singer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SingerRepository extends CrudRepository<Singer, Long> {
	List<Singer> findByFirstName(String firstName);
}
