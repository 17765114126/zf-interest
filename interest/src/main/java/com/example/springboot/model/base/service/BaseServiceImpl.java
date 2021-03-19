package com.example.springboot.model.base.service;

import com.example.springboot.model.base.dao.BaseDao;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseServiceImpl implements BaseService{

	public abstract BaseDao getDao();
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public <T> int deleteByPrimaryKey(Object id) {
		return getDao().deleteByPrimaryKey(id);
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public <T> int insert(T t) {
		return getDao().insert(t);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public <T> int insertSelective(T t) {
		return getDao().insertSelective(t);
	}
	
	@Override
	public <T> T selectByPrimaryKey(Object id) {
		return getDao().selectByPrimaryKey(id);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public <T> int updateByPrimaryKey(T t) {
		return getDao().updateByPrimaryKey(t);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public <T> int updateByPrimaryKeySelective(T t) {
		return getDao().updateByPrimaryKeySelective(t);
	}
	
	
}
