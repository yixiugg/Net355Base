/**
 * Copyright (c) 2011 State Street Bank and Trust Corp.
 * 225 Franklin Street, Boston, MA 02110, U.S.A.
 * All rights reserved.
 *
 * "BeansFactory.java is the copyrighted,
 * proprietary property of State Street Bank and Trust Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date            Programmer              Notes
 * ---------    ---------------------  --------------------------------------------
 * Jan 27, 2011    Yixiu Jiang             initial
 */
package com.betball.util;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeansFactory {

	private static final String APPLICATION_CONTEXT_FILENAME = "applicationContext.xml";

	private static Log log = LogFactory.getLog(BeansFactory.class);

	private static ApplicationContext appContext;

	private static ServletContext context;

	private static BeanFactory factory = null;
	static {
		try {
			appContext = new ClassPathXmlApplicationContext(
					APPLICATION_CONTEXT_FILENAME);
			// factory = new FileSystemXmlApplicationContext(
			// APPLICATION_CONTEXT_FILENAME);

		} catch (Exception e) {
			log.info("Spring load error ", e);
		}
	}

	public static void setContext(ServletContext context) {
		BeansFactory.context = context;
	}

	private static ApplicationContext getApplicationContext() {
		return appContext;

	}

	/**
	 * @param name
	 * @return
	 */
	public static Object get(String name) {
		Object o = null;
		try {
			// o = factory.getBean(name);
			try {
				o = getApplicationContext().getBean(name);
				System.out.println("object:" + o);
			} catch (Exception e) {
				o = factory.getBean(name);
				System.out.println("object2:" + o);
			}

		} catch (Exception e) {
			System.out.println("bean name is " + name);
			e.printStackTrace();
			log.info("Get bean error, Spring bean name is " + name, e);
		}
		return o;

	}

}
