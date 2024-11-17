package vip.fairy.repos;

import org.springframework.data.repository.CrudRepository;
import vip.fairy.entities.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
}
