package com.hugui.api.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @date : 2017年8月31日
 * @author : hugui
 * @description : TODO
 **/

public class AccessFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info("send {} request to {}", request.getMethod(), request.getRequestURI().toString());

		Object accessToken = request.getParameter("token");

		if (accessToken == null) {
			log.warn("access token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			return null;
		}

		log.warn("access token is ok");
		return null;
	}

	public boolean shouldFilter() {
		// 确认该过滤器的请求范围,return false表示不执行该过滤器
		return true;
	}

	@Override
	public int filterOrder() {
		// 多个过滤器时从低到高依次执行
		return 0;
	}

	@Override
	public String filterType() {
		// pre 		-> 表示在请求被路由之前执行
		// routing 	-> 表示在请求被路由时执行
		// post 	-> 表示在routing和error过滤器之后执行
		// error 	-> 表示处理请求时发生错误时执行
		return "pre";
	}

}
