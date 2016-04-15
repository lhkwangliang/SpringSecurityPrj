package com.wl.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wl.dao.CalendarUserDao;
import com.wl.dao.CalendarRoleDao;
import com.wl.entity.CalendarUser;
import com.wl.utils.CalendarUserAuthorityUtils;
import com.wl.utils.MD5Util;

public class CalendarUserDetailsService implements UserDetailsService {

	private CalendarUserDao calendarUserDao;
	
	private CalendarRoleDao calendarRoleDao;
	
	@Autowired
	public void setCalendarUserDao(CalendarUserDao calendarUserDao) {
		this.calendarUserDao = calendarUserDao;
	}

	@Autowired
	public void setCalendarRoleDao(CalendarRoleDao calendarRoleDao) {
		this.calendarRoleDao = calendarRoleDao;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		CalendarUser user = calendarUserDao.findUserByEmail(username);
		if (user == null) {
            throw new UsernameNotFoundException("找不到用户");
        } else {
        	String salt = user.getEmail();
        	List<String> list = calendarRoleDao.getListByUserName(user.getEmail());
        	String[] array = new String[list.size()];
        	for (int i=0;i<list.size();i++) {
				String string = list.get(i);
				array[i] = string;
			}
            return new CalendarUserDetails(user, salt, array);
        }
	}
	
	public boolean registerUser(CalendarUser user) {
		CalendarUser u = calendarUserDao.findUserByEmail(user.getEmail());
		if(u == null) {
			user.setPassword(MD5Util.MD5WithSalt(user.getPassword(), user.getEmail()));
			return calendarUserDao.insert(user);
		}else{
			return false;
		}
	}
	
	public class CalendarUserDetails extends CalendarUser implements UserDetails {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String[] array;
		private String salt;
		
        CalendarUserDetails(CalendarUser user, String salt, String[] array) {
            setId(user.getId());
            setEmail(user.getEmail());
            setFirstName(user.getFirstName());
            setLastName(user.getLastName());
            setPassword(user.getPassword());
            setSalt(salt);
            
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
		public String getSalt() {
			return salt;
		}
		public void setSalt(String salt) {
			this.salt = salt;
		}
        
    }

}
