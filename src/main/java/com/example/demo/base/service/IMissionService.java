package com.example.demo.base.service;

import java.util.List;
import java.util.Map;

import com.example.demo.base.vo.MissionVo;

public interface IMissionService {
	public List<MissionVo> list();
	public int writeDao(Map<String, String> map);
	public int delete(Integer Id);
}
