package com.example.demo;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcSingerDao implements SingerDao, InitializingBean {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject("SELECT CONCAT(last_name , ' ', first_name) FROM singer WHERE id = ?", new Object[]{id}, String.class);
    }

    @Override
    public String findFirstNameById(Long id) {
        return jdbcTemplate.queryForObject("SELECT first_name FROM singer WHERE id = ?", new Object[]{id}, String.class);
    }

    @Override
    public void afterPropertiesSet() {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("Null JdbcTemplate on SingerDao");
        }
    }
}
