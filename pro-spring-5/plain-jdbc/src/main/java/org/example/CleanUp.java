package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class CleanUp {
	private static final Logger logger = LoggerFactory.getLogger(CleanUp.class);

	private final JdbcTemplate jdbcTemplate;

	public CleanUp(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private void destroy() {
		logger.info(" ... Deleting database files.");
		jdbcTemplate.execute("DROP ALL OBJECTS DELETE FILES;");
	}
}
