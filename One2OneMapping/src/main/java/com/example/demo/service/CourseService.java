package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.CourseDTO;

public interface CourseService {

	CourseDTO addCourse(CourseDTO courseDTO);

	List<CourseDTO> getAllCourses();

	CourseDTO getCourseById(Long courseId);

	CourseDTO updateCourse(Long courseId, CourseDTO courseDTO);

	CourseDTO partialUpdateCourse(Long courseId, Map<String, Object> courseDTO);

	boolean deleteCourseById(Long courseId);

}
