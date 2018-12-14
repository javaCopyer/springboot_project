package com.zc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class MyInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ecception)
			throws Exception {
		 System.out.println("========afterCompletion=========");
	        Long start = (Long) request.getAttribute("startTime");
	        System.out.println("耗时:"+(System.currentTimeMillis() - start));
	        
	        System.out.println(ecception);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
		System.out.println("========postHandle=========");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("耗时:"+(System.currentTimeMillis() - start));
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 System.out.println("========preHandle=========");
	        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
	        System.out.println(((HandlerMethod)handler).getMethod().getName());
	        request.setAttribute("startTime", System.currentTimeMillis());
	        
	        return true;
	}

}
