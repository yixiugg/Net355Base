/**
 * Copyright (c) 2012 Net355 team.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "BaseDAO.java is the copyrighted,
 * proprietary property of Net355 team and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-4           yixiugg                      initial
 **/

package com.net355.dao;

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
 * *BaseDAO.java
 */
public interface BaseDAO {
	public void save(Object entity);

	public void delete(Object entity);

	public void deleteAll(Collection entities);
	
	public void deleteByHql(String hql);

	public Object findById(Class clazz, String id);

	public void loadById(Object obj, Serializable id);

	public void refresh(Object entity);

	public List findByExample(Object example);

	public List findAll(Class clazz);

	public void saveOrUpdate(Object entity);
	
	public void saveOrUpdateAll(Collection entities);

	public void update(Object entity);

	public List findByHql(String hql);

	public List<?> find(String hql, Object... values);

	public int bulkUpdate(String hql, Object... values);

	public List<?> query(String sql, RowMapper rowMapper);
	
	public Object query(String sql,ResultSetExtractor rse);

	public int[] batchUpdate(String[] sql);

	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria);

	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int startIndex);

	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex);
	
	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex, ResultTransformer resultTransformer);

	public List findAllByCriteria(final DetachedCriteria detachedCriteria);

	public int getCountByCriteria(final DetachedCriteria detachedCriteria);

	public void callProcedure(String procString, List<Object> params)
			throws Exception;

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception;

	public List findByCriteria(final DetachedCriteria detachedCriteria);
	
	public int getStatBySql(String sql);
}
