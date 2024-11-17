package org.example.dao;


import org.example.entities.Singer;

import java.util.List;

public interface SingerDao {

	List<Singer> findAll();

	void insert(Singer singer);

	void delete(Long singerId);
}

