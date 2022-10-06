package com.example.demo.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.base.dao.IUserDao;
import com.example.demo.base.vo.UserVo;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	IUserDao dao;

	@Override
	public List<UserVo> listDao() {
		List<UserVo> list = dao.listDao();
		return list;
	}

	@Override
	public int writeDao(Map<String, String> map) {
		int nResult = dao.writeDao(map);
		return nResult;
	}
	
}