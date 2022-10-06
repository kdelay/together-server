package com.example.demo.base.service;

import java.util.List;
import java.util.Map;

import com.example.demo.base.vo.GalleryVo;

public interface IGalleryService {
	public List<GalleryVo> list();
	public int writeDao(Map<String, String> map);
	public int delete(Map<String, String> map);
}
