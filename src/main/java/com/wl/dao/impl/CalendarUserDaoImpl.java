package com.wl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.wl.dao.CalendarUserDao;
import com.wl.entity.CalendarUser;

public class CalendarUserDaoImpl implements CalendarUserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CalendarUser findUserByEmail(String username) {
		String sql = "select id, email, password, firstname, lastname from calendar_user where email = ?";
		List matches = jdbcTemplate.query(sql, new Object[] { username }, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
				CalendarUser user = new CalendarUser();
				user.setId(rs.getInt(1));
				user.setEmail(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				return user;
			}
		});
		return matches.size() > 0 ? (CalendarUser) matches.get(0) : null;
	}

	public boolean insert(CalendarUser user) {
		String sql = "insert into calendar_user (id, email, password, firstname, lastname) values(?,?,?,?,?)";
		int id = new Random().nextInt(100000);
		int result = jdbcTemplate.update(sql,
				new Object[] { id, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName() });
		return result > 0 ? true: false;
	}

}
