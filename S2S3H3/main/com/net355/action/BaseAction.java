
package com.net355.action;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.net355.common.PaginationSupport;
import com.net355.service.BaseService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * *NewBaseAction.java
 */
@SuppressWarnings("serial")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,
		Preparable, ServletContextAware {
	public static final int DEFAULT_PAGESIZE = 20;

	protected static final String VIEW = "view";

	protected static final String LIST = "list";

	protected static final String QUERY = "query";

	protected static final String EDIT = "edit";

	protected static final String ADD = "add";

	protected static final String JSON = "json";

	protected static final String REMOVE = "remove";

	protected static final String DETAIL = "detail";

	private String targetModule;

	protected ServletContext servletContext;
	@Autowired
	protected BaseService baseService;


	protected String domain;
	public String redirectDomain;

	protected final org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(this.getClass());


	protected Class<T> entityClass;
	protected T entity;
	protected String id;
	protected int page = 1;
	protected int startIndex = 0;
	protected int pageSize = DEFAULT_PAGESIZE;

	protected PaginationSupport pageList;
	protected PaginationSupport<T> pageSupport;

	public List<T> resultList;

	protected String result;

	public String callback;

	@SuppressWarnings("unchecked")
	public BaseAction() {
		try {
			entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			log.info("未能获取泛型类");
		}
	}

	public T getModel() {
		if (entity == null) {
			try {
				entity = (T) entityClass.newInstance();
			} catch (Exception e) {
				log.info("无法创建模型实例");
				return null;
			}
		}
		return entity;
	}

	public HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected ServletContext getApplication() {
		ServletContext app = ServletActionContext.getServletContext();
		return app;
	}

	protected void output(Object s) throws IOException {
		getResponse().setContentType("text/html;charset=utf-8");
		getResponse().getWriter().print(s);
	}

	@SuppressWarnings("unchecked")
	public void prepare() throws Exception {
		if (this.id != null && id.length() > 0) {
			if (entityClass != null)
				entity = (T) baseService.findById(entityClass, id);
		} else {
			entity = (T) entityClass.newInstance();
			prepareModelInner(entity);
		}
	}

	/**
	 * @param model
	 */
	protected void prepareModelInner(T model) {

	}

	protected void setRedirectDomain(String redirect) {
		if (redirect != null) {
			redirectDomain = redirect;
			return;
		}
		Cookie[] cookie = getRequest().getCookies();
		for (Cookie c : cookie) {
			if (c.getName().equals("redirectDomain"))
				redirectDomain = c.getValue();
		}
	}

	protected void deposit(String key, Object o) {
		getRequest().setAttribute(key, o);
	}

	public Serializable save() {
		return SUCCESS;
	}

	@SkipValidation
	public String view() {
		return VIEW;
	}

	public String edit() {
		return EDIT;
	}

	public String list() {
		return LIST;
	}

	public String query() {
		return QUERY;
	}

	public String add() {
		return ADD;
	}

	public String remove() {
		return REMOVE;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public PaginationSupport<?> getPageList() {
		return pageList;
	}

	public void setPageList(PaginationSupport pageList) {
		this.pageList = pageList;
	}

	public PaginationSupport<T> getPageSupport() {
		return pageSupport;
	}

	public void setPageSupport(PaginationSupport<T> pageSupport) {
		this.pageSupport = pageSupport;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public String getTargetModule() {
		return targetModule;
	}

	public void setTargetModule(String targetModule) {
		this.targetModule = targetModule;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		if (page > 0)
			startIndex = (page - 1) * pageSize;
	}


	public void reloadSession(String SessionKey, Object obj) {
		getSession().setAttribute(SessionKey, obj);
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public boolean bitAnd(Integer i, Integer j) {
		return (i & j) == 0 ? false : true;
	}
}
