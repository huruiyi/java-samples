package com.in28minutes.repository;

import com.in28minutes.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
//@RunWith(SpringRunner.class)  //JUnit 4
@ExtendWith(SpringExtension.class)
class PersonMongoDbRepositoryTest {

  @Autowired
  PersonMongoDbRepository personRepository;

  @Test
  void simpleTest() {
    personRepository.deleteAll();
    personRepository.save(new Person("name1"));
    personRepository.save(new Person("name2"));

    for (Person person : personRepository.findAll()) {
      System.out.println(person);
    }

    System.out.println(personRepository.findByName("name1"));

    System.out.println(personRepository.count());
  }
}