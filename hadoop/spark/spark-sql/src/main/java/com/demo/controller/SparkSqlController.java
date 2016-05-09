package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.SparkSqlService;

@RestController
public class SparkSqlController {

	@Autowired
	private SparkSqlService sparkSqlService;
	
	public Object sql(String sql){
		return sparkSqlService.sql(sql);
	}
}
