package com.example.demo.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.base.dao.ICalendarDao;
import com.example.demo.base.vo.CalendarVo;

@Service
public class CalendarServiceImpl implements ICalendarService{
	@Autowired
	ICalendarDao dao;
	
	@Override
	public List<CalendarVo> list() {
		List<CalendarVo> list = dao.listDao();
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