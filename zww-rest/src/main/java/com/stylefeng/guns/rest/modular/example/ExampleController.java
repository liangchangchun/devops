package com.stylefeng.guns.rest.modular.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@RestController
@RequestMapping("/example")
public class ExampleController {

	 @RequestMapping(value = "/users",method = RequestMethod.GET)
	 public String getUser(){
	        return "Welcome";
	 }
    
}
