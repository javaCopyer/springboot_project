package com.zc;


import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * springmvc的配置
 * @author sky
 *
 */
@Configuration
public class SpringmvcConfig extends WebMvcConfigurerAdapter{

	/**视图解析器**/
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		return new BeanNameViewResolver();
	}
	
	/**
	 *使用fastjson的HttpMessageConverter @ResponseBody注解返回json
	 * @return
	 */
  @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
	  	
	  	FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
        return new HttpMessageConverters(converter);

    }
}
