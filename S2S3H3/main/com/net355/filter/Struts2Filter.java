
package com.net355.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.net355.util.StringUtil;


/**
 ** Struts2Filter.java
 **/
public class Struts2Filter extends StrutsPrepareAndExecuteFilter {
	private String notFilterDir = "";
	protected Log log = LogFactory.getLog(this.getClass());

	public void destroy() {
		notFilterDir = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String uri = req.getRequestURI();
			//log.info(req.getHeader("X-Real-IP"));
			decodeGet(req);
			String[] notFilterDirs = notFilterDir.split(",");
			for (int i = 0; i < notFilterDirs.length; i++) {
				String notFilterDirValue = notFilterDirs[i];
				if (uri.indexOf(notFilterDirValue) != -1) {
					chain.doFilter(request, response);
					return;
				}
			}
			super.doFilter(request, response, chain);
		} catch (Exception e) {
			log.error(e);
		}
	}

	private void decodeGet(HttpServletRequest request) {
		if (request.getMethod().equals("GET")) {
			Map<String, String[]> map = request.getParameterMap();
			for (Map.Entry<String, String[]> entry : map.entrySet()) {
				String[] values = entry.getValue();
				for (int i = 0; i < values.length; i++) {
					values[i] = StringUtil.decode(values[i]);
				}
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		notFilterDir = filterConfig.getInitParameter("notFilterDir");
		super.init(filterConfig);
	}
}
