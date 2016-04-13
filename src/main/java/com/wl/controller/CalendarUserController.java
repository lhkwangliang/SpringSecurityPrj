package com.wl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wl.entity.CalendarUser;
import com.wl.service.CalendarUserDetailsService;

@Controller
public class CalendarUserController {
	
	private CalendarUserDetailsService calendarUserDetailsService;

	@Autowired
	public void setCalendarUserDetailsService(CalendarUserDetailsService calendarUserDetailsService) {
		this.calendarUserDetailsService = calendarUserDetailsService;
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	protected ModelAndView regPost(final HttpServletRequest reqeuest, CalendarUser u) {
		ModelAndView mav = new ModelAndView();
		
		CalendarUser user = new CalendarUser();
		user.setEmail(u.getEmail());
		user.setPassword(u.getPassword());
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		if(calendarUserDetailsService.registerUser(user)){
			
			UserDetails userDetails = calendarUserDetailsService.loadUserByUsername(user.getEmail());   
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(),userDetails.getAuthorities());   
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			mav.setViewName("redirect:main.jsp");
		}else{
			mav.setViewName("redirect:register.jsp");
		}
		return mav;
	}
}
