package com.wl.security;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import com.wl.utils.MD5Util;

public class MyPasswordEncode extends MessageDigestPasswordEncoder {
	
	public MyPasswordEncode(String algorithm) {
		super(algorithm);
	}
	
	@Override
	// 如果返回true，则验证通过
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		return encPass.equalsIgnoreCase(MD5Util.MD5WithSalt(rawPass, salt.toString()));
	}

}
