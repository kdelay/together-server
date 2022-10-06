package com.example.demo.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.base.vo.UserVo;

@Mapper
public interface IUserDao {
	public List<UserVo> listDao();
	public int writeDao(Map<String, String> map);
}