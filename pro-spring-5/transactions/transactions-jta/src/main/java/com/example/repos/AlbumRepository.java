package com.example.repos;

import com.example.entities.Album;
import com.example.entities.Singer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

  List<Album> findBySinger(Singer singer);
}
