package com.example.demo.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.base.dao.IGalleryDao;
import com.example.demo.base.vo.GalleryVo;

@Service
public class GalleryServiceImpl implements IGalleryService{
	@Autowired
	IGalleryDao dao;

	@Override
	public List<GalleryVo> list() {
		List<GalleryVo> list = dao.listDao();
		return list;
	}
	
	@Override
	public int writeDao(Map<String, String> map) {
		int nResult = dao.writeDao(map);
		return nResult;
	}
	
	@Override
	public int delete(Map<String, String> map) {
		int result = dao.delete(map);
		return result;
	}
}
