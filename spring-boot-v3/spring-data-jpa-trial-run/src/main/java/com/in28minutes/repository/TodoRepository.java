package com.in28minutes.repository;

import com.in28minutes.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
//	List<Todo> findByTitleAndDescription(String title, String description);
//
//	List<Todo> findDistinctTodoByTitleOrDescription(String title,
//			String description);
//
//	List<Todo> findByTitleIgnoreCase(String title, String description);
//
//	List<Todo> findByTitleOrderByIdDesc(String lastname);
//
//	List<Todo> findByIsDoneTrue(String lastname);

}
