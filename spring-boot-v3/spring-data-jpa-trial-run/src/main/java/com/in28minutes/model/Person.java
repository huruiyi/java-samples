package com.in28minutes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Person {

	@Id
	private String id;

	private String name;

	public Person() {// Make JPA Happy

	}

	public Person(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Person [id=%s, name=%s]", id, name);
	}
}
