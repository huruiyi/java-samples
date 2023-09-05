package com.example.services;

import com.example.entities.Singer;

import java.util.List;

public interface SingerService {

	List<Singer> findAll();

	Singer findById(Long id);

	Singer save(Singer singer);

	long countAll();
}
