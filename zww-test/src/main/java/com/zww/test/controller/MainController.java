package com.zww.test.controller;

import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zww.test.entity.Order;

@Controller
public class MainController {
	
	 private static final String STR_REDIS_KEY = "key:name";

	@Autowired
	private StringRedisTemplate redisTemplate;

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("name", "梁长春");
		return "index";
	}
	
	@GetMapping("/aop")
	public String aop(Model model) {
		return "aoptest";
	}
	
	@GetMapping("/valid")
	public String valid(Model model) {
		return "validtest";
	}
	
	 @GetMapping("/redis")
	    public String index() {
	        return "redistest";
	    }
	
	 @PostMapping("/setString")
	    public @ResponseBody Map<String, Object> setString(String value) {
	        redisTemplate.opsForValue().set(STR_REDIS_KEY, value);
	        Map<String, Object> map = new HashMap<>();
	        map.put("msg", "ok");
	        return map;
	    }

	    @PostMapping("/getString")
	    public @ResponseBody Map<String, Object> getString() {
	        String value = redisTemplate.opsForValue().get(STR_REDIS_KEY);
	        Map<String, Object> map = new HashMap<>();
	        map.put("value", value);
	        map.put("msg", "ok");
	        return map;
	    }
	
	 @PostMapping("/aopsave")
	public @ResponseBody Map<String, Object> save() {
	        Map<String, Object> map = new HashMap<>();
	        map.put("msg", "ok");
	        return map;
	    }
	 
	@PostMapping("/saveOrder")
	public @ResponseBody Map<String, Object> saveOrder(@RequestBody @Valid Order order) {
	        Map<String, Object> map = new HashMap<>();
	        map.put("msg", "ok");
	        map.put("order", order);
	        return map;
	}

	
	 @GetMapping("/jquery")
	    public String jquery() {
	        return "jquery";
	    }

	    @GetMapping("/angularjs")
	    public String angularjs() {
	        return "angularjs";
	    }

	    @PostMapping("/postData")
	    public @ResponseBody Map<String, Object> postData(String no, int quantity, String date) {
	        System.out.println("no:" + no);
	        System.out.println("quantity:" + quantity);
	        System.out.println("date:" + date);
	        Map<String, Object> map = new HashMap<>();
	        map.put("msg", "ok");
	        map.put("quantity", quantity);
	        map.put("no", no);
	        map.put("date", date);
	        return map;
	    }

	    @PostMapping("/postJson")
	    public @ResponseBody Map<String, Object> postJson(@RequestBody Order order) {
	        System.out.println("order no:" + order.no);
	        System.out.println("order quantity:" + order.quantity);
	        System.out.println("order date:" + order.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	        Map<String, Object> map = new HashMap<>();
	        map.put("msg", "ok");
	        map.put("value", order);
	        return map;
	    }
	    
	    
}
