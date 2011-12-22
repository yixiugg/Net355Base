/**
 * Copyright (c) 2012 Net355 team.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "UserMapper.java is the copyrighted,
 * proprietary property of Net355 team and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * Dec 22, 2011           yixiu                      initial
 **/

package com.net355.mapper;

import com.net355.models.User;
  
public interface UserMapper {  
	
    public void insertUser(User user);  
  
    public User getUser(String name);  
}  