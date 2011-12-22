package com.net355.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.net355.models.Admin;
import com.net355.service.BaseService;

@Controller
public class HelloController {
	@Resource
	private BaseService baseService;

	@RequestMapping(value="/{name}")
	
	public String printWelcome(@PathVariable String name,ModelMap model) {
		model.addAttribute("message", "Hello, " + name);
		return "hello";
	}

	@RequestMapping("/hello")
	public String hello(ModelMap model) {
		String name = (String) model.get("name");
		model.addAttribute("message", "Hello, " + name);
		return "hello";
	}

	@RequestMapping("/getAdmin/{adminId}")
	public @ResponseBody
	Admin getAdmin(@PathVariable("adminId") String adminId) {
		Admin admin = (Admin) baseService.findById(Admin.class,
				adminId);
		return admin;
	}
}