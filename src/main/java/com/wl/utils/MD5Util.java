package com.wl.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class MD5Util {

	public static String MD5WithSalt(String rawPass, String salt) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		return md5.encodePassword(rawPass, salt);
	}
}
