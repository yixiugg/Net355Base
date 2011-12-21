package com.betball.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betball.models.BetAdmin;
import com.betball.service.BaseService;

@Controller
@RequestMapping("/hello")
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
	BetAdmin getAdmin(@PathVariable("adminId") Integer adminId) {
		BetAdmin admin = (BetAdmin) baseService.findById(BetAdmin.class,
				adminId);
		return admin;
	}
}