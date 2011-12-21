/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "IdentifierGeneratorHelper.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-3           yixiugg                      initial
 **/
package com.betball.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.transform.ResultTransformer;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.betball.dao.BaseDAO;
import com.betball.util.BeansFactory;


/**
 * A data access object (DAO) providing persistence and search support for
 * Object entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.abbcc.dao.impl.Object
 * @author MyEclipse Persistence Tools
 */

public class BaseDAOImpl implements BaseDAO {
	private static Log LOG = LogFactory.getLog(BaseDAOImpl.class);

	protected final org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(this.getClass());

	// Hibernate的Template
	private HibernateTemplate hibernateTemplate;


	// 设置Hibernate
	public void setSessionFactory(SessionFactory sessionFactory) {
		if (this.hibernateTemplate == null) {
			this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		} else {
			this.hibernateTemplate.setSessionFactory(sessionFactory);
		}
	}


	// 获取Hibernate的模板
	protected HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}


	/**
	 * 获得单例
	 * 
	 * @return
	 */
	public static BaseDAOImpl getInstance() {
		return (BaseDAOImpl) BeansFactory.get("baseDAO");
	}

	/**
	 * 保存进数据库，同时写入索引文件
	 * 
	 * @param transientInstance
	 */
	public void save(Object transientInstance) {
		LOG.debug("saving Object instance");
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.getCurrentSession();
			FullTextSession fullTextSession = Search
					.createFullTextSession(session);
			fullTextSession.save(transientInstance);

			LOG.debug("save successful");
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}
	}

	/**
	 * 删除
	 * 
	 * @param persistentInstance
	 */
	public void delete(Object persistentInstance) {
		LOG.debug("deleting Object instance");
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.getCurrentSession();
			FullTextSession fullTextSession = Search
					.createFullTextSession(session);
			fullTextSession.delete(persistentInstance);
			LOG.debug("delete successful");
		} catch (RuntimeException re) {
			LOG.error("delete failed", re);
			throw re;
		}
	}

	public void deleteAll(Collection entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	/**
	 * 根据主键查找
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public Object findById(Class clazz, Integer id) {
		LOG.debug("getting Object instance with id: " + id);
		try {
			Object instance = (Object) getHibernateTemplate().get(clazz, id);
			return instance;
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

}