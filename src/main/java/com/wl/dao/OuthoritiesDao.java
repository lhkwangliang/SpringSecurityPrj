package com.wl.dao;

import java.util.List;

public interface OuthoritiesDao {
	
	public List<String> getListByUserName(String username);

}
