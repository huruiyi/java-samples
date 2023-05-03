package com.example.services;

import com.example.entities.Album;
import com.example.entities.Singer;

import java.util.List;

public interface AlbumService {
	List<Album> findBySinger(Singer singer);

	List<Album> findByTitle(String title);
}
