package com.example.web;


import com.example.entities.Singer;
import com.example.entities.Singers;
import com.example.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/singer")
public class SingerController {

  final Logger logger = LoggerFactory.getLogger(SingerController.class);

  @Autowired
  private SingerService singerService;

  @GetMapping(value = "/listdata")
  public Singers listData() {
    return new Singers(singerService.findAll());
  }

  @GetMapping(value = "/{id}")
  public Singer findSingerById(@PathVariable Long id) {
    return singerService.findById(id);
  }

  @PostMapping(value = "/")
  public Singer create(@RequestBody Singer singer) {
    logger.info("Creating singer:{} ", singer);
    singerService.save(singer);
    logger.info("Singer created successfully with info: {}", singer);
    return singer;
  }

  @PutMapping(value = "/{id}")
  public void update(@RequestBody Singer singer, @PathVariable Long id) {
    logger.info("Updating singer: {}", singer);
    singerService.save(singer);
    logger.info("Singer updated successfully with info: {}", singer);
  }

  @DeleteMapping(value = "/{id}")
  public void delete(@PathVariable Long id) {
    logger.info("Deleting singer with id: {}", id);
    Singer singer = singerService.findById(id);
    singerService.delete(singer);
    logger.info("Singer deleted successfully");
  }
}
