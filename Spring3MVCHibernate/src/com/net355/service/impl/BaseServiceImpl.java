/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-6           yixiugg                      initial
 **/

package com.net355.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;

import com.net355.dao.BaseDAO;
import com.net355.service.BaseService;

/**
 * *AdminServiceImpl.java
 */
public class BaseServiceImpl implements BaseService {
	protected BaseDAO baseDAO;

	protected final org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(this.getClass());

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public void save(Object transientInstance) {
		baseDAO.save(transientInstance);
	}

	public void delete(Object persistentInstance) {
		baseDAO.delete(persistentInstance);
	}

	public void deleteAll(Collection entities) {
		baseDAO.deleteAll(entities);
	}

	@SuppressWarnings("unchecked")
	public Object findById(Class clazz, String id) {
		return baseDAO.findById(clazz, id);
	}

}
