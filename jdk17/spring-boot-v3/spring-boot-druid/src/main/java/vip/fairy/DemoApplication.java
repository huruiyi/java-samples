package vip.fairy;

import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

  public final EntityManager entityManager;

  public DemoApplication(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }


  @Override
  public void run(String... args) {
    entityManager.createQuery("select Id, Name, CountryCode, District, Population from City ").getHints();
  }
}
