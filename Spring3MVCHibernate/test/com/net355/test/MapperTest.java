package com.net355.test;

import com.net355.mapper.UserMapper;
import com.net355.models.Admin;
import com.net355.models.User;
import com.net355.service.BaseService;
import com.net355.util.BeansFactory;

public class MapperTest {
	public static void main(String[] args){
		UserMapper userMapper = (UserMapper)BeansFactory.get(UserMapper.class);  
	    User user = new User();
	    user.setUserId("222");
	    user.setUserAcc("test1");
	    user.setAccPwd("aaa");
	    userMapper.insertUser(user);  
		}
}
