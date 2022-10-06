package com.example.demo.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.base.vo.GalleryVo;

@Mapper
public interface IGalleryDao {
	public List<GalleryVo> listDao();
	public int writeDao(Map<String, String> map);
	public int delete(Map<String, String> map);
}
