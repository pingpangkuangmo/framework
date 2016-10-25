package com.demo.zuul.netflix;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.netflix.zuul.http.ZuulServlet;

@EnableZuulProxy
@SpringCloudApplication
public class Application {
	
	ZuulServlet zuulServlet;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }

}
