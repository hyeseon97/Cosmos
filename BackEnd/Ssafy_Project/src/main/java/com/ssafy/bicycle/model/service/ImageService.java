package com.ssafy.bicycle.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.bicycle.model.dto.CourseMap;
import com.ssafy.bicycle.model.dto.Image;

public interface ImageService {

	// 이미지 등록
	int writeImage(Image image);
	
	// 이미지 조회
	List<Image> getImageList(HashMap<String, Integer> map);
	
	
}
