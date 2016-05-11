package com.demo.spring.boot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@EnableAutoConfiguration(exclude={EmbeddedServletContainerFactory.class}) 
public class Application{
	
	private static SparkSqlService sparkSqlService;
	
	private static boolean run = true;
	
	@RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello Spring boot and spark-sql!";
    }
	
	@RequestMapping("/sql_test")
    @ResponseBody
	public Object sqlTest() {
        return sparkSqlService.getData("show tables");
    }
	
	@RequestMapping("/sql")
    @ResponseBody
    Object sql(@RequestBody Map<String, Object> data) {
		String sql = (String) data.get("sql");
		Assert.notNull(sql, "sql can not be null");
        return sparkSqlService.getData(sql);
    }

    public static void main(String[] args) throws Exception {
    	sparkSqlService = new SparkSqlService();
    	sparkSqlService.init();
    	
    	Runtime.getRuntime().addShutdownHook(new Thread(){

			@Override
			public void run() {
				System.out.println("ShutdownHook : shutdown");
				run = false;
			}
    		
    	});
    	
		Map<String, Object> map = new HashMap<>();
		map.put("key1", "value1");
		System.out.println(JSON.toJSONString(map, true));
		System.out.println("start success !");
        SpringApplication.run(Application.class, args);
        while(run){
        	Thread.sleep(20000);
        }
    }
    
}
