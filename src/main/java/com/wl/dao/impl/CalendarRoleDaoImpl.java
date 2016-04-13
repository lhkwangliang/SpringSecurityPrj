package com.wl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.wl.dao.CalendarRoleDao;

public class CalendarRoleDaoImpl implements CalendarRoleDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<String> getListByUserName(String username) {
		String sql = "select authority from authorities where username = ?";
		List<String> matches = jdbcTemplate.query(sql, new Object[] { username }, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
				return rs.getString(1);
			}
		});
		return matches;
	}

	

}
