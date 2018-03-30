package com.stylefeng.guns.rest.game.engine;

import java.util.ArrayList;
import java.util.List;

import com.stylefeng.guns.rest.game.engine.model.Type;


public class ZwwGame implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id ;
	private String code ;
	private String name ;
	
	private List<Type> types = new ArrayList<Type>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Type> getTypes() {
		return types;
	}
	public void setTypes(List<Type> types) {
		this.types = types;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
