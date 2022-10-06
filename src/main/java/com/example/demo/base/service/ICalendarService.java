package com.example.demo.base.service;

import java.util.List;
import java.util.Map;

import com.example.demo.base.vo.CalendarVo;

public interface ICalendarService {
	public List<CalendarVo> list();
	public int writeDao(Map<String, String> map);
	public int delete(Integer Id);
}
