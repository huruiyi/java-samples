package com.example.profiles.service.impl;

import com.example.profiles.Food;
import com.example.profiles.service.FoodProviderService;
import java.util.ArrayList;
import java.util.List;

public class KinderGartenFoodProviderServiceImpl implements FoodProviderService {
    @Override
    public List<Food> provideLunchSet() {
        List<Food> lunchSet = new ArrayList<>();
        lunchSet.add(new Food("kid-Milk"));
        lunchSet.add(new Food("kid-Biscuits"));

        return lunchSet;
    }
}
