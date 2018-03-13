package com.ben.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.ben.web.service.UserService;

public class UserServiceImpl implements UserService{

	public Map<String, String> getUser() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Yam");
		map.put("age", "22");
		return map;
	}

}
