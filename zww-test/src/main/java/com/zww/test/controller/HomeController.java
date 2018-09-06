package com.zww.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zww.test.Respository.OrderRepository;
import com.zww.test.dao.OrderDao;
import com.zww.test.entity.Order;

@Controller
public class HomeController {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderDao orderDao;

    @GetMapping("/")
    public String index() {
        return "index";
    }
  

    /**
     * 处理日期类型
     * 
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 持久化
     * 
     * @param entity
     * @return
     * @throws Exception 
     */
    @PostMapping("/save")
    public @ResponseBody Map<String, Object> save(@RequestBody Order entity) throws Exception {
        Map<String, Object> result = new HashMap<>();
        entity = orderDao.save(entity);
        result.put("id", entity.id);
        return result;
    }

    /**
     * 获取对象
     * 
     * @param id
     * @return
     */
    @PostMapping("/get")
    public @ResponseBody Object get(String id) {
        return orderRepository.findOne(id);
    }
    
	@PostMapping("/getRedis")
	public @ResponseBody Order getRedis(@RequestParam String id) {
	    return orderDao.get(id);
	}

    /**
     * 获取全部
     * 
     * @return
     */
    @PostMapping("/findAll")
    public @ResponseBody Object findAll() {
        return orderRepository.findAll();
    }

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public @ResponseBody Map<String, Object> delete(String id) {
        Map<String, Object> result = new HashMap<>();
        orderRepository.delete(id);
        result.put("id", id);
        return result;
    }
}
