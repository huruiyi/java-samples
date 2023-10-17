package com.example.services;


import com.example.entities.Car;
import com.example.repos.CarRepository;
import com.google.common.collect.Lists;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("carService")
@Repository
@Transactional
public class CarServiceImpl implements CarService {

  public boolean done;

  final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

  final CarRepository carRepository;

  public CarServiceImpl(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Car> findAll() {
    return Lists.newArrayList(carRepository.findAll());
  }

  @Override
  public Car save(Car car) {
    return carRepository.save(car);
  }

  @Override
  public void updateCarAgeJob() {
    List<Car> cars = findAll();

    DateTime currentDate = DateTime.now();
    logger.info("Car age update job started");

    cars.forEach(car -> {
      int age = Years.yearsBetween(car.getManufactureDate(), currentDate).getYears();

      car.setAge(age);
      save(car);
      logger.info("Car age update --> " + car);
    });

    logger.info("Car age update job completed successfully");
    done = true;
  }

  @Override
  public boolean isDone() {
    return done;
  }
}
