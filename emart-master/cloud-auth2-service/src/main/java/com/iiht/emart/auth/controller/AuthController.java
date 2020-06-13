package com.iiht.emart.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
	private static final String KEY_USER = "user";
	private static final String KEY_AUTHORITIES = "authorities";

	/**
	 * 当试图访问由OAuth2保护的服务时，将会调用到这个方法
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping(value= {"/user"}, produces="application/json")
	public Map<String, Object> auth(OAuth2Authentication user){
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put(KEY_USER, user.getUserAuthentication().getPrincipal());
		userInfo.put(KEY_AUTHORITIES, AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));

		return userInfo;
	}
}
