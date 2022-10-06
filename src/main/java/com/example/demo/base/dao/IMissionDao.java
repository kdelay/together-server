package com.example.demo.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.base.vo.MissionVo;

@Mapper
public interface IMissionDao {
	public List<MissionVo> listDao();
	public int writeDao(Map<String, String> map);
	public int delete(Integer Id);
}
