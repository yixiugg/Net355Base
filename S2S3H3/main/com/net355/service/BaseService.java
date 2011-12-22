/**
 * Copyright (c) 2012 Net355 team.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminService.java is the copyrighted,
 * proprietary property of Net355 team and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-6           yixiugg                      initial
 **/

package com.net355.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.net355.common.PaginationSupport;


/**
 * *AdminService.java
 */
public interface BaseService {
	public void save(Object entity);

	public void delete(Object entity);
	
	public void deleteAll(Collection entites);

	@SuppressWarnings("unchecked")
	public Object findById(Class clazz, String id);

	public void load(Object obj, Serializable id);
	
	public void refresh(Object entity);

	@SuppressWarnings("unchecked")
	public List findByExample(Object example);

	public List findByHql(String hql);
	
	public int getStatBySql(String sql);

	@SuppressWarnings("unchecked")
	public List findAll(Class clazz);

	public void saveOrUpdate(Object entity);
	
	public void saveOrUpdateAll(Collection entities);

	public void update(Object entity);

	public List<?> find(String hql, Object... values);

	public int bulkUpdate(String hql, Object... values);

	public List<?> query(String sql, RowMapper rowMapper);
	
	public Object query(String sql,ResultSetExtractor rse);

	public int[] batchUpdate(String[] sql);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex,
			ResultTransformer resultTransformer);
	

	@SuppressWarnings("unchecked")
	public List findAllByCriteria(DetachedCriteria detachedCriteria);

	public int getCountByCriteria(DetachedCriteria detachedCriteria);

	public void callProcedure(String procString, List<Object> params)
			throws Exception;

	@SuppressWarnings("unchecked")
	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception;

	public List findByCriteria(DetachedCriteria detachedCriteria);
	
	public List findByParamsAndIndex(String field, String keyword, Class clazz)throws ParseException, IOException ;
}
