package com.example.mapper;

import com.example.model.Country;

import java.util.List;

public interface CountryMapper {

  List<Country> selectAll();
}