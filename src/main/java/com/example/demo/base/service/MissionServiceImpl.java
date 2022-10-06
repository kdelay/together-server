package com.example.demo.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.base.dao.IMissionDao;
import com.example.demo.base.vo.MissionVo;

@Service
public class MissionServiceImpl implements IMissionService{
	@Autowired
	IMissionDao dao;
	
	@Override
	public List<MissionVo> list() {
		List<MissionVo> list = dao.listDao();
		return list;
	}

	@Override
	public int writeDao(Map<String, String> map) {
		int nResult = dao.writeDao(map);
		return nResult;
	}
	
	@Override
	public int delete(Integer Id) {
		int result = dao.delete(Id);
		return result;
	}
}
