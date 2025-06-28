package com.example.demo.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFound;
import com.example.demo.repository.StudentRepository;
import com.example.demo.utility.StudentUtility;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public StudentDTO addStudent(StudentDTO studentDTO) {

		return StudentUtility.mapToStudentDTO(studentRepository.save(StudentUtility.mapToStudent(studentDTO)));

	}

	@Override
	public List<StudentDTO> getAllStudent() {
		List<StudentDTO> studentDTOList = new ArrayList<>();
		List<Student> studentList = studentRepository.findAll();

		for (Student student : studentList) {
			studentDTOList.add(StudentUtility.mapToStudentDTO(student));
		}

		return studentDTOList;
	}

	@Override
	public StudentDTO getStudentById(Integer studentId) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFound("Student with gien Id is not found"));

		return StudentUtility.mapToStudentDTO(student);
	}

	@Override
	public StudentDTO updateStudent(Integer studentId, StudentDTO studentDTO) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFound("Student with gien Id is not found"));

		Student newUpdatedStudent = StudentUtility.mapToStudent(studentDTO);

		student = newUpdatedStudent;
		student.setStudentId(studentId);

		return StudentUtility.mapToStudentDTO(studentRepository.save(student));

	}

	@Override
	public StudentDTO partialUpdateStudent(Integer studentId, Map<String, Object> studentDTO) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFound("Student with gien Id is not found"));

		studentDTO.forEach((FieldName, Value) -> {

			Field field = ReflectionUtils.findField(Student.class, FieldName);
			if (field != null) {
				field.setAccessible(true);
				ReflectionUtils.setField(field, student, Value);
			}

		});

		return StudentUtility.mapToStudentDTO(studentRepository.save(student));

	}

	@Override
	public boolean deleteStudentById(Integer studentId) {

		boolean flag = false;
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFound("Student with gien Id is not found"));

		if (student != null) {
			studentRepository.delete(student);
			flag = true;
		}

		return flag;
	}

}
