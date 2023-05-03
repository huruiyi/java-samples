package com.example.demo.chapter_2;


import com.example.demo.chapter_2.domain.Salary;
import com.example.demo.chapter_2.persistance.SalaryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class SalaryRepositoryIntegrationTest {

    @Autowired
    private SalaryRepository salaryRepository;

    @Test
    public void shouldSaveSalaryAndRetrieveItByUserId() {
        //given
        String userId = "joseph";
        Salary salary = new Salary(userId, "124215", "t5356315", 23L);

        //when
        salaryRepository.save(salary);
        List<Salary> salaries = salaryRepository.findByUserId(userId);

        //then
        assertThat(salaries.get(0).getAccountFrom()).isEqualTo("124215");
        assertThat(salaries.get(0).getAccountTo()).isEqualTo("t5356315");
    }

    @Test
    public void shouldRetrieveAllSalariesThatHave123AsAccountFrom() {
        Salary salary0 = new Salary(UUID.randomUUID().toString(), "123", "55555", 23L);
        Salary salary1 = new Salary(UUID.randomUUID().toString(), "123", "77777", 23L);
        Salary salary2 = new Salary(UUID.randomUUID().toString(), "77777", "2145", 23L);
        List<Salary> salaries = Arrays.asList(salary0, salary1, salary2);

        salaryRepository.saveAll(salaries);
        List<Salary> foundSalaries = salaryRepository.findByFromAccount("123");

        //then
        assertThat(foundSalaries.size()).isEqualTo(2);
    }

}