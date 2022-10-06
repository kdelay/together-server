package com.example.demo.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.base.vo.CalendarVo;

@Mapper
public interface ICalendarDao {
	public List<CalendarVo> listDao();
	public int writeDao(Map<String, String> map);
	public int delete(Integer Id);
}
