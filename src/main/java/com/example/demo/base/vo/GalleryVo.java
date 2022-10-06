package com.example.demo.base.vo;

import lombok.Data;

@Data
public class GalleryVo {
	private String fileUniqueName;
	private String fileTitle;
	private String fileContent;
	private String uploadFolder;
	private String uploadTime;
}