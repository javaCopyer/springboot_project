package com.zc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zc.entity.User;
import com.zc.service.TestAutoService;
/**
 * 
 * 
 *
 */







@Controller	           //相当于@controller和@Respondy
//@EnableAutoConfiguration  //启动根据下载的jar springboot的自动配置
//@ComponentScan  //将@service @component， @controller, @configuration， @bean等注解的bean扫描进spring容器中
@SpringBootApplication(exclude=org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class)  //（相当于@EnableAutoConfiguration + @ComponentScan + @springconfiguration）
//@Import(value={WebConfig.class}) //引入@Configuration注解的WebConfig类， 
public class DemoApplication extends SpringBootServletInitializer{
	
	private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	@Value("${user.name}")
	private String name;
	@Value("${user.id}")
	private int id;

	@Autowired
	private TestAutoService testAutoService;
	
	@Bean
    public Runnable createRunnable(){
        return new Runnable() {
			@Override
			public void run() {
				System.out.println("spring boot is running");
			}
		};
    }
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "test";
	}
	
	@RequestMapping("/hello")
	public String hello(Model model) {
		logger.info("使用thymeleaf....");
//		User user = new User();
//		user.setBirthday(new Date());
//		user.setPassword("123");
//		user.setId(1);
//		user.setUsername(testAutoService.getMsg());
//		return user;
		model.addAttribute("name", "daji");
		return "hello";
	}
		@RequestMapping("/jspview")
		public String jspview(Model model) {
			logger.info("使用jsp....");
			model.addAttribute("name", "i am jsp！");
			return "jspview";	
		
		
	}	
	@RequestMapping(value="/each")
	public ModelAndView each() {
		ModelAndView modelAndView = new ModelAndView();
		List<User> list = new ArrayList<User>();
		for(int i=0; i<5; i++){
			User user = new User();
			user.setId(i);		
			list.add(user);
		}
		modelAndView.addObject("users", list).addObject("current", new Date()).setViewName("each");
		return modelAndView;
	}
	
	
	
	   @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(DemoApplication.class);
	    }
	
	  public static void main(String[] args) throws Exception {
	        SpringApplication.run(DemoApplication.class, args);
	    }
}
