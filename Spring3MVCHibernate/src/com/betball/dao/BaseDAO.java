/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "BaseDAO.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-4           yixiugg                      initial
 **/

package com.betball.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;

/**
 * *BaseDAO.java
 */
public interface BaseDAO {
	public void save(Object entity);

	public void delete(Object entity);

	public void deleteAll(Collection entities);
	
	public Object findById(Class clazz, Integer id);

}
