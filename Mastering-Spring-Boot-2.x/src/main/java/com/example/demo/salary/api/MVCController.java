package com.example.demo.salary.api;

import com.example.demo.chapter_2.domain.Salary;
import com.example.demo.chapter_2.domain.SalaryDto;
import com.example.demo.chapter_2.persistance.SalaryRepository;
import com.example.demo.eventbus.api.EventBus;
import com.example.demo.eventbus.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class MVCController {

    @Autowired
    private SalaryRepository salaryRepository;

    @PostConstruct
    private void init() {
        salaryRepository.save(new Salary("T1", "dummyFrom", "dummyTo", 100L));
    }

    @Autowired
    private EventBus eventBus;

    @RequestMapping("/mvc/all-salaries")
    public String indexView(Model model) {
        Iterable<Salary> list = salaryRepository.findAll();
        model.addAttribute("list", list);
        System.out.println(list);
        return "allsalaries";
    }

    @PostMapping("/mvc/salary")
    public String salarySubmit(@ModelAttribute SalaryDto salaryDto, Model model) {
        Salary salary = new Salary(salaryDto.getUserId(), salaryDto.getAccountFrom(), salaryDto.getAccountTo(), salaryDto.getAmount());
        salaryRepository.save(salary);

        Event save = new Event("SAVE", "Save salary" + salaryDto);
        eventBus.publish(save);

        Iterable<Salary> salaries = salaryRepository.findAll();
        model.addAttribute("list", salaries);
        System.out.println(salaries);
        return "allsalaries";
    }

    @GetMapping("/mvc/createSalary")
    public String salaryForm(Model model) {
        model.addAttribute("salaryDto", new SalaryDto());
        return "create";
    }
}
