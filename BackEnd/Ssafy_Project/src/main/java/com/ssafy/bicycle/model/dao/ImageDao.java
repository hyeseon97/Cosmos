package com.ssafy.bicycle.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.bicycle.model.dto.Image;

public interface ImageDao {
	
	int insertImage(Image image);

	List<Image> selectAll(HashMap<String, Integer> map);

}
