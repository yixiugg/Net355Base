package com.net355.action;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.net355.models.User;
import com.net355.service.BaseService;

public class UserAction extends BaseAction<User>{

	@Resource
	private BaseService baseService;
	public String get(){
		User user =(User) baseService.findById(getEntityClass(), "111");
		try {
			JsonConfig jsonConfig = new JsonConfig();  
			jsonConfig.setExcludes( new String[]{ "syscode","viewlogs","messagesForReceiverId","recharges","messagesForSenderId",
					"expenses","files","spaces","products","brands","folders","newses" });  
			this.result=JSONObject.fromObject(user,jsonConfig).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON;
	}
}
