package com.example.demo.base.service;

import java.util.List;
import java.util.Map;

import com.example.demo.base.vo.UserVo;

public interface IUserService {
	public List<UserVo> listDao();
	public int writeDao(Map<String, String> map);
}
