package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.StudentDTO;

public interface StudentService {

	StudentDTO addStudent(StudentDTO studentDTO);

	List<StudentDTO> getAllStudent();

	StudentDTO getStudentById(Integer studentId);

	StudentDTO updateStudent(Integer studentId, StudentDTO studentDTO);

	StudentDTO partialUpdateStudent(Integer studentId, Map<String, Object> studentDTO);

	boolean deleteStudentById(Integer studentId);

	

}
