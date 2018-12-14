package com.zc;


import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zc.dao.UserDao;
import com.zc.filter.TimeFilter;
import com.zc.service.UserService;
import com.zc.servlet.Servlet1;
@Configuration
public class WebConfig{
	@Bean
	public UserDao getUserDao() {
		return new UserDao();
	}
	@Bean
	public UserService getUserService() {
		UserService userService = new UserService();
		userService.setUserDao(getUserDao());
		return userService;
	}
		/**
	   * 将servlet注册成bean
	   * @return
	   */
	  @Bean
	  public ServletRegistrationBean servletRegistrationBean() {
		  return new ServletRegistrationBean(new Servlet1(), "/servlet1");  
	  }
	
	  /**
	   * 注册filter
	   * @return
	   */
	  @Bean
	  public FilterRegistrationBean timeFilter() {
	      FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	      
	      TimeFilter timeFilter = new TimeFilter();
	      registrationBean.setFilter(timeFilter);
	      
	      List<String> urls = new ArrayList<String>();
	      urls.add("/*");
	      registrationBean.setUrlPatterns(urls);
	      
	      return registrationBean;
	  }
}
