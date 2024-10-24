package vip.fairy.services;


import vip.fairy.entities.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car save(Car car);

    void updateCarAgeJob();

    boolean isDone();
}
