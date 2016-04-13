package com.wl.utils;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class CalendarUserAuthorityUtils {
	
	public static Collection<GrantedAuthority> createAuthorities(UserDetails userDetails, String[] array){
		List<GrantedAuthority> authorities;
		if(array == null || array.length == 0){
			authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
		}else{
			authorities = AuthorityUtils.createAuthorityList(array);
		}
		return authorities;
	}

}
