package com.zww.test.dao;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zww.test.Respository.OrderRepository;
import com.zww.test.entity.Order;
import com.mysql.jdbc.StringUtils;

@Repository
@Transactional(readOnly = false, rollbackFor = Throwable.class)
public class OrderDao {
	@Autowired
    private OrderRepository orderRepository;

    /**
     * 获取全部
     * 
     * @return
     */
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * 持久化
     * 
     * @param entity
     * @return
     */
    public Order save(Order entity) throws Exception {
        // 持久化
    	Order result = orderRepository.save(entity);
        // 如果持久化成功，就抛出异常。如果开启事务，那么刚才持久化的数据应回滚
       // if (!StringUtils.isNullOrEmpty(entity.id))
       //     throw new Exception("测试无法持久化第一条数据的异常");
        
        return result;
    }
    
    /**
     * 假设从数据库获取的订单数据
     * 
     * @param id
     * @return
     */
    @Cacheable(value = "order", key = "'.id.'+#id")
    public Order get(String id) {
        Order order = new Order();
        order.id = id;
        order.no = "No.00001";
        order.date = new Date();
        order.quantity = 100;
        return order;
    }
}
