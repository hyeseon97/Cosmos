package com.ssafy.bicycle.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.bicycle.model.dao.ImageDao;
import com.ssafy.bicycle.model.dto.Image;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDao imageDao;
	
	@Override
	public int writeImage(Image image) {
		return imageDao.insertImage(image);
	}

	@Override
	public List<Image> getImageList(HashMap<String, Integer> map) {
		return imageDao.selectAll(map);
	}

}
