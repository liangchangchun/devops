package com.stylefeng.guns.modular.code.service;

import com.baomidou.mybatisplus.mapper.SqlRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取数据库所有的表
 *
 * @author fengshuonan
 * @date 2017-12-04-下午1:37
 */
@Service
public class TableService {

    @Value("${spring.datasource.db-name}")
    private String dbNames;

    /**
     * 获取当前数据库所有的表信息
     */
    public List<Map<String, Object>> getAllTables() {
    	List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
    	String[] dbs = dbNames.split(",");
    	 String sql = "";
    	for (int i=0;i<dbs.length;i++) {
    		sql = "select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = '" + dbs[i] + "'";
    		List<Map<String, Object>> result = SqlRunner.db().selectList(sql);
    		for (Map<String, Object> e:result) {
    			results.add(e);
    		}
    	}
       // String sql = "select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = '" + dbName + "'";
        return results;
    }
}
