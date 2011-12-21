package com.betball.test;

import com.betball.models.BetAdmin;
import com.betball.service.BaseService;
import com.betball.util.BeansFactory;

public class DAOTest {
	public static void main(String[] args){
		BetAdmin admin = new BetAdmin("test","testpwd");
		BaseService baseService = (BaseService)BeansFactory.get("baseService");
		baseService.save(admin);
		}
}
