package vip.fairy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vip.fairy.model.Person;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {

    /**
     * http://localhost:8080/mvc/app/hello
     * <p>
     * http://localhost:8080/mvc/app/
     *
     * @return
     */
    //@RequestMapping(value = {"/", "/hello"})
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello,你好！！";
    }

    @GetMapping(value = "/person_json", produces = "application/json;charset=utf-8")
    public List<Person> personsJson() {
        return Arrays.asList(new Person(1L, "张三", 18, "广州"), new Person(2L, "李四", 20, "上海"), new Person(3L, "王五", 30, "河南"));
    }

    @GetMapping(value = "/person_xml", produces = "application/xml;charset=utf-8")
    public List<Person> personsXml() {
        return Arrays.asList(new Person(1L, "张三", 18, "广州"), new Person(2L, "李四", 20, "上海"), new Person(3L, "王五", 30, "河南"));
    }

    /**
     * http://localhost:8080/mvc/app/person
     * Person 必须包含无参构造函数，否则会无法解析数据（HttpMessageConversionException）
     */
    @PostMapping(path = "/person", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
    public Person add(@RequestBody Person person) {
        System.out.println(person);
        return person;
    }

}
