package org.example.dao;

import org.example.entities.Singer;

import java.util.List;

public interface SingerDao {

	List<Singer> findAll();

	List<Singer> findAllWithAlbum();

	Singer findById(Long id);

	void save(Singer singer);

	void delete(Singer singer);
}
