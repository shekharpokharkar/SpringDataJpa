package com.example.demo.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.demo.dto.CourseDTO;
import com.example.demo.entity.Course;

import com.example.demo.repository.CourseRepository;
import com.example.demo.utility.CourseUtility;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

	@Override
	public CourseDTO addCourse(CourseDTO courseDTO) {
		Course course = CourseUtility.mapToCourse(courseDTO);
		Course saved = courseRepository.save(course);
		return CourseUtility.mapToCourseDTO(saved);
	}

	@Override
	public List<CourseDTO> getAllCourses() {
		List<CourseDTO> courseDTOList = new ArrayList<>();
		List<Course> courseList = courseRepository.findAll();

		for (Course course : courseList) {
			courseDTOList.add(CourseUtility.mapToCourseDTO(course));
		}
		return courseDTOList;
	}

	@Override
	public CourseDTO getCourseById(Long courseId) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFound("Course with given Id not found"));
		return CourseUtility.mapToCourseDTO(course);
	}

	@Override
	public CourseDTO updateCourse(Long courseId, CourseDTO courseDTO) {
		courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound("Course with given Id not found"));

		Course updatedCourse = CourseUtility.mapToCourse(courseDTO);
		updatedCourse.setCourseId(courseId);

		return CourseUtility.mapToCourseDTO(courseRepository.save(updatedCourse));
	}

	@Override
	public CourseDTO partialUpdateCourse(Long courseId, Map<String, Object> updates) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFound("Course with given Id not found"));

		updates.forEach((fieldName, value) -> {
			Field field = ReflectionUtils.findField(Course.class, fieldName);
			if (field != null) {
				field.setAccessible(true);
				ReflectionUtils.setField(field, course, value);
			}
		});

		return CourseUtility.mapToCourseDTO(courseRepository.save(course));
	}

	@Override
	public boolean deleteCourseById(Long courseId) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFound("Course with given Id not found"));

		courseRepository.delete(course);
		return true;
	}
}
