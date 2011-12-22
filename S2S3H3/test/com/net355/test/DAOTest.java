package com.net355.test;

import com.net355.models.Admin;
import com.net355.service.BaseService;
import com.net355.util.BeansFactory;

public class DAOTest {
	public static void main(String[] args){
		Admin admin = new Admin();
		admin.setAdminAcc("net355admin");
		BaseService baseService = (BaseService)BeansFactory.get("baseService");
		baseService.save(admin);
		}
}
