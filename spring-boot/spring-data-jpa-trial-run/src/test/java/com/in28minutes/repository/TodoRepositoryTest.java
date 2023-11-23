package com.in28minutes.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.in28minutes.SpringDataJpaFirstExampleApplication;
import com.in28minutes.model.Todo;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringDataJpaFirstExampleApplication.class)
public class TodoRepositoryTest {

	@Autowired
	TodoRepository todoRepository;

	@Test
	public void check_todo_count() {
		assertEquals(3, todoRepository.count());
	}

	@Test
	public void findOne() {
		Optional<Todo> todo = todoRepository.findById(101L);
		assertEquals("Todo Desc 1", todo.get().getDescription());
	}

	@Test
	public void exists() {
		assertFalse(todoRepository.existsById(105L));
		assertTrue(todoRepository.existsById(101L));
	}

	@Test
	public void delete() {
		todoRepository.deleteById(101L);
		assertEquals(2,todoRepository.count());
	}

	@Test
	public void deleteAll() {
		todoRepository.deleteAll();
		assertEquals(0,todoRepository.count());
	}

	@Test
	public void save() {
		Todo todo = todoRepository.findById(101L).get();
		todo.setDescription("Todo Desc Updated");
		todoRepository.save(todo);
		

		Todo updatedTodo = todoRepository.findById(101L).get();
		assertEquals("Todo Desc Updated",updatedTodo.getDescription());
	}

}