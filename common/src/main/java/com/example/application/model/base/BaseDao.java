package com.example.application.model.base;


public interface BaseDao {
	
	public <T> int insert(T t);
	
	public <T> int insertSelective(T t);
	
	public <T> int deleteByPrimaryKey(Object id);
	
	public <T> int updateByPrimaryKeySelective(T t);
	
	public <T> int updateByPrimaryKey(T t); 	
	
	public <T> T selectByPrimaryKey(Object id);
	
	
}
