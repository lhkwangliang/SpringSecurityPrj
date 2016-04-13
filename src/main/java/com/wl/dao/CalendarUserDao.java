package com.wl.dao;

import com.wl.entity.CalendarUser;

public interface CalendarUserDao {

	public CalendarUser findUserByEmail(String username);
	
	public boolean insert(CalendarUser user);
}
