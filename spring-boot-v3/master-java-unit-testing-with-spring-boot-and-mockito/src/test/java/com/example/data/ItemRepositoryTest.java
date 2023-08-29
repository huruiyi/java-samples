package com.example.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.model.Item;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ItemRepositoryTest {
	
	@Autowired
	private ItemRepository repository;
	
	@Test
	public void testFindAll() {
		List<Item> items = repository.findAll();
		assertEquals(3,items.size());
	}

	@Test
	public void testFindOne() {
		Item item = repository.findById(10001).get();
		
		assertEquals("Item1",item.getName());
	}

}
