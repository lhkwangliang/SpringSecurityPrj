package com.wl.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wl.dao.CalendarUserDao;
import com.wl.dao.OuthoritiesDao;
import com.wl.entity.CalendarUser;
import com.wl.utils.CalendarUserAuthorityUtils;

public class CalendarUserDetailsService implements UserDetailsService {

	private CalendarUserDao calendarUserDao;
	
	private OuthoritiesDao outhoritiesDao;
	
	@Autowired
	public void setCalendarUserDao(CalendarUserDao calendarUserDao) {
		this.calendarUserDao = calendarUserDao;
	}

	@Autowired
	public void setOuthoritiesDao(OuthoritiesDao outhoritiesDao) {
		this.outhoritiesDao = outhoritiesDao;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		CalendarUser user = calendarUserDao.findUserByEmail(username);
		if (user == null) {
            throw new UsernameNotFoundException("找不到用户");
        } else {
        	List<String> list = outhoritiesDao.getListByUserName(user.getEmail());
        	String[] array = new String[list.size()];
        	for (int i=0;i<list.size();i++) {
				String string = list.get(i);
				array[i] = string;
			}
            return new CalendarUserDetails(user, array);
        }
	}
	
	public boolean registerUser(CalendarUser user) {
		CalendarUser u = calendarUserDao.findUserByEmail(user.getEmail());
		if(u == null) {
			return calendarUserDao.insert(user);
		}else{
			return false;
		}
	}
	
	private final class CalendarUserDetails extends CalendarUser implements UserDetails {  
		private String[] array;
		
        CalendarUserDetails(CalendarUser user, String[] array) {  
            setId(user.getId());  
            setEmail(user.getEmail());  
            setFirstName(user.getFirstName());  
            setLastName(user.getLastName());  
            setPassword(user.getPassword());  
            
            this.array = array;
        }
        public Collection<GrantedAuthority> getAuthorities() {
            return CalendarUserAuthorityUtils.createAuthorities(this, array);  
        }
        public String getUsername() {  
            return getEmail();  
        }  
        public boolean isAccountNonExpired() {  
            return true;  
        }  
        public boolean isAccountNonLocked() {  
            return true;  
        }  
        public boolean isCredentialsNonExpired() {  
            return true;  
        }  
        public boolean isEnabled() {  
            return true;  
        }  
    }

}
