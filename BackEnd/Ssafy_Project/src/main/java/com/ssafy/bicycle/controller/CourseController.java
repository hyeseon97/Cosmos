package com.ssafy.bicycle.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.bicycle.model.dto.Course;
import com.ssafy.bicycle.model.dto.CourseMap;
import com.ssafy.bicycle.model.dto.SearchCondition;
import com.ssafy.bicycle.model.service.CourseMapService;
import com.ssafy.bicycle.model.service.CourseService;



@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseMapService courseMapService;
	
	private int courseNum = 15;
	
//	 등록
//	 지도에 체크를 해서 정보를 입력하고 넣겠지??
//	 courseMap이랑 course 필요하네
//	 경도 위도 값이 같이 들어올거임, 그리고 제목 타이들 등등이 들어올거임
	@PostMapping("/course")
	public ResponseEntity<?> writeCourse(@RequestBody Course course){
		System.out.println(course);
		int result1 = courseService.writeCourse(course);
		boolean result2 = true;
		for(int seq = 0;seq<course.getCourseMap().size()/2;seq++) {
			CourseMap cm = new CourseMap(courseNum, seq+1, course.getCourseMap().get(seq*2), course.getCourseMap().get(seq*2+1));
			if(courseMapService.writeCourseMap(cm)==0) {
				result2 = false;
			}
		}
		courseNum++;
		System.out.println(result1);
		System.out.println(result2);
		if(result1==1 && result2) {
			return new ResponseEntity<Course>(course,HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//다시
	// 조회 or 조회
	@GetMapping("/course")
	public ResponseEntity<?> list(SearchCondition condition){
		List<Course> list = courseService.search(condition);
		for(int i = 0;i<list.size();i++) {
			int courseNum = list.get(i).getCourse_num();
			
			List<CourseMap> cmlist = courseMapService.getCourseMapList(courseNum);
			
			List<Double> courseMap = new ArrayList<Double>();
			
			System.out.println("i=" + i + "   courseNum=" + courseNum + "   " + cmlist.size());
			System.out.println(list.get(i));
			for(int seq = 0;seq<cmlist.size();seq++) {
				System.out.println(cmlist.get(seq));
				courseMap.add(cmlist.get(seq).getCm_lat());
				courseMap.add(cmlist.get(seq).getCm_lng());
			}
			 
			list.get(i).setCourseMap(courseMap);
		}
		
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Course>>(list, HttpStatus.OK);
	}
	
	// 키워드 조회
	@GetMapping("/course/keyword")
	public ResponseEntity<?> list(String keyword){
		List<Course> list = courseService.search(null);
		for(int i = 0;i<list.size();i++) {
			int courseNum = list.get(i).getCourse_num();
			
			List<CourseMap> cmlist = courseMapService.getCourseMapList(courseNum);
			
			List<Double> courseMap = new ArrayList<Double>();
			
			System.out.println("i=" + i + "   courseNum=" + courseNum + "   " + cmlist.size());
			System.out.println(list.get(i));
			for(int seq = 0;seq<cmlist.size();seq++) {
				System.out.println(cmlist.get(seq));
				courseMap.add(cmlist.get(seq).getCm_lat());
				courseMap.add(cmlist.get(seq).getCm_lng());
			}
			 
			list.get(i).setCourseMap(courseMap);
		}
		
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Course>>(list, HttpStatus.OK);
	}
	
	
//	
//	//다시
//	// 상세조회
//	@GetMapping("/course/{num}")
//	public ResponseEntity<?> detail(@PathVariable int num){
//		Course course = courseService.getCourseOne(num);
//		
//		if(course == null) {
//			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//		}
//		
//		return new ResponseEntity<Course>(course,HttpStatus.OK);
//	}
//	
//	// 수정
//	@PutMapping("/course")
//	public ResponseEntity<?> update(@RequestBody Course course){
//		if(courseService.modifyCourse(course)) {
//			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
//	}
//	
//	
//	// 삭제
//	@DeleteMapping("/course/{num}")
//	public ResponseEntity<?> delete(@RequestBody int num) {
//		if (courseService.removeCourse(num)) {
//			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
//	}
//	
}
