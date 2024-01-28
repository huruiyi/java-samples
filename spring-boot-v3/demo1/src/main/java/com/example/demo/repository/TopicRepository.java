package com.example.demo.repository;

import com.example.demo.model.Topic;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long>, ExtendedRepository<Topic, Long> {

  List<Topic> findByDescriptionLikeIgnoreCase(String description);
}
