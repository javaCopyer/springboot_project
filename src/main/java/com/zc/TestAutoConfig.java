package com.zc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zc.config.TestAutoPropertiesVal;
import com.zc.service.TestAutoService;

/**
 * 代码解释
 * 自动配置TestAutoService 将bean自动放入ioc容器中管理
 * 也是模仿springboot自动化配置
 * 步骤：1.创建一个配置类 @configuration， 判断类路径下有没有TestAutoService（@ConditionalOnClass）
 * 		 2.创建META-INF/spring.factories(org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
										 com.zc.TestAutoConfig)
 * 		 即自动将TestAutoService放入spring容器中
 * @author sky
 *
 */


@Configuration  
@EnableConfigurationProperties(TestAutoPropertiesVal.class) //注解解释：开启
@ConditionalOnClass(TestAutoService.class)  //注解解释：检查类路径下有没有TestAutoService
@ConditionalOnProperty(prefix="test.auto", value="enabled", matchIfMissing=true)
public class TestAutoConfig {
	@Autowired  
	private TestAutoPropertiesVal pv;
	
	@Bean
	@ConditionalOnMissingBean(TestAutoService.class)  //注解解释：容器中没有TestAutoService则实例化该bean
	public TestAutoService testAutoService() {
		TestAutoService service = new TestAutoService();
		service.setMsg(pv.getName());
		return service;
	}
}
