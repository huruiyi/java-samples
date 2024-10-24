package com.example.profiles.service.impl;

import com.example.profiles.Food;
import com.example.profiles.service.FoodProviderService;
import java.util.ArrayList;
import java.util.List;

public class HighSchoolFoodProviderServiceImpl implements FoodProviderService {

  @Override
  public List<Food> provideLunchSet() {
    List<Food> lunchSet = new ArrayList<>();
    lunchSet.add(new Food("hs-Coke"));
    lunchSet.add(new Food("hs-Hamburger"));
    lunchSet.add(new Food("hs-French Fries"));

    return lunchSet;
  }
}
