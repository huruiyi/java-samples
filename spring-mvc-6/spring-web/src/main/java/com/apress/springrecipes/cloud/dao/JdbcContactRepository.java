package com.apress.springrecipes.cloud.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.apress.springrecipes.cloud.ContactRepository;
import com.apress.springrecipes.cloud.model.Contact;

@Service
public class JdbcContactRepository extends JdbcDaoSupport implements ContactRepository {

	@Autowired
	public JdbcContactRepository(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public List<Contact> findAll() {
		return getJdbcTemplate().query("select id, name, email from contact", (rs, rowNum) -> {
			Contact contact = new Contact();
			contact.setId(rs.getLong(1));
			contact.setName(rs.getString(2));
			contact.setEmail(rs.getString(3));
			return contact;
		});
	}

	@Override
	public void save(Contact c) {
		getJdbcTemplate().update("insert into contact (name, email) values (?, ?)", c.getName(), c.getEmail());
	}

}
