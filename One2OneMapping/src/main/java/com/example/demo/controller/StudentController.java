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

import com.example.demo.dto.StudentDTO;
import com.example.demo.exception.StudentNotDeleted;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/student/v1")
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping("/")
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {

		StudentDTO response = service.addStudent(studentDTO);

		return new ResponseEntity<StudentDTO>(response, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<StudentDTO>> getAllStudent() {

		List<StudentDTO> response = service.getAllStudent();

		return new ResponseEntity<List<StudentDTO>>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Integer studentId) {

		StudentDTO response = service.getStudentById(studentId);

		return new ResponseEntity<StudentDTO>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Integer studentId,
			@RequestBody StudentDTO studentDTO) {

		StudentDTO response = service.updateStudent(studentId, studentDTO);

		return new ResponseEntity<StudentDTO>(response, HttpStatus.ACCEPTED);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<StudentDTO> partialUpdateStudent(@PathVariable("id") Integer studentId,
			@RequestBody Map<String, Object> studentDTO) {

		StudentDTO response = service.partialUpdateStudent(studentId, studentDTO);

		return new ResponseEntity<StudentDTO>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudentById(@PathVariable("id") Integer studentId) {

		boolean response = service.deleteStudentById(studentId);
		if (!response) {
			throw new StudentNotDeleted("Student Not Found");
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
