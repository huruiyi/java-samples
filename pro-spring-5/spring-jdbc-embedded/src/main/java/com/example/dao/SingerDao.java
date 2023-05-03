package com.example.dao;

import com.example.entities.Singer;

import java.util.List;

public interface SingerDao {


    String findNameById(Long id);

    String findNameById2(Long id);

    String findFirstNameById(Long id);


    List<Singer> findAll();

    List<Singer> findAllWithAlbums();

}

