package com.example.demo.dao;

import com.example.demo.entities.Singer;

import java.util.List;

public interface SingerDao {

    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);



    String findFirstNameById(Long id);

    void insert(Singer singer);

    void update(Singer singer);

    void delete(Long singerId);

    List<Singer> findAllWithAlbums();

    void insertWithAlbum(Singer singer);
}

