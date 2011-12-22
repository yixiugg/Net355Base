package com.net355.test;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;

import com.net355.models.Admin;
import com.net355.service.BaseService;
import com.net355.util.BeansFactory;

public class DAOTest {
	public static void main(String[] args) throws ParseException, IOException{
//		Admin admin = new Admin();
//		admin.setAdminAcc("net355admin");
//		admin.setAdminName("This is my 中文！");
//		admin.setAdminEmail("test@china.com");
		BaseService baseService = (BaseService)BeansFactory.get("baseService");
		List admins = baseService.findByParamsAndIndex("adminName", "This", Admin.class);
		for(Object admin : admins){
			System.out.println(admin);
		}
		}
}
