package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CourseDTO;
import com.example.demo.exception.CourseNotDeleted;
import com.example.demo.service.CourseService;

@RestController
@RequestMapping("/course/v1")
public class CourseController {

	@Autowired
	private CourseService service;

	// Create a new Course
	@PostMapping("/")
	public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO courseDTO) {
		CourseDTO response = service.addCourse(courseDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// Get all Courses
	@GetMapping("/")
	public ResponseEntity<List<CourseDTO>> getAllCourses() {
		List<CourseDTO> response = service.getAllCourses();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Get Course by ID
	@GetMapping("/{id}")
	public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") Long courseId) {
		CourseDTO response = service.getCourseById(courseId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Update Course fully
	@PutMapping("/{id}")
	public ResponseEntity<CourseDTO> updateCourse(@PathVariable("id") Long courseId, @RequestBody CourseDTO courseDTO) {
		CourseDTO response = service.updateCourse(courseId, courseDTO);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	// Partial Update Course
	@PatchMapping("/{id}")
	public ResponseEntity<CourseDTO> partialUpdateCourse(@PathVariable("id") Long courseId,
			@RequestBody Map<String, Object> courseDTO) {
		CourseDTO response = service.partialUpdateCourse(courseId, courseDTO);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	// Delete Course by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourseById(@PathVariable("id") Long courseId) {
		boolean response = service.deleteCourseById(courseId);
		if (!response) {
			throw new CourseNotDeleted("Course Not Found");
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
