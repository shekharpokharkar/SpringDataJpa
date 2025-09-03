package com.example.demo.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Address;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFound;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.utility.StudentUtility;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

	private AddressRepository addressRepository;
	private StudentRepository studentRepository;

	@Override
	public StudentDTO addStudent(StudentDTO studentDTO) {

		Student student = StudentUtility.mapToStudent(studentDTO);
		Student save = studentRepository.save(student);
		StudentDTO mapToStudentDTO = StudentUtility.mapToStudentDTO(save);
		return mapToStudentDTO;
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

		student.setMarried(studentDTO.isMarried());
		student.setStudentDOB(studentDTO.getStudentDOB());
		student.setStudentName(studentDTO.getStudentName());
		student.setStudentPassword(studentDTO.getStudentPassword().toCharArray());
		student.setStudentPercentage(studentDTO.getStudentPercentage());
		student.setStudentSalary(studentDTO.getStudentSalary());

		if (studentDTO.getCourseDTO() != null) {
			List<CourseDTO> courseDTO = studentDTO.getCourseDTO();
			List<Course> courseList = new ArrayList<>();
			for (CourseDTO dto : courseDTO) {
				Course course = new Course();
				course.setCourseDuration(dto.getCourseDuration());
				course.setCourseGrade(dto.getCourseGrade());
				course.setCourseName(dto.getCourseName());
				course.setCourseStartingDate(dto.getCourseStartingDate());
				course.setCourseTeacher(dto.getCourseTeacher());
				course.setStudent(student);
				courseList.add(course);
			}
			student.setCourse(courseList);
		}

		if (studentDTO.getStudentAddress() != null) {
			AddressDTO studentAddress = studentDTO.getStudentAddress();
			Address address = new Address();
			address.setCity(studentAddress.getCity());
			address.setLaneName1(studentAddress.getLaneName1());
			address.setLaneName2(studentAddress.getLaneName2());
			address.setState(studentAddress.getState());
			address.setStudent(student);
			address.setZipCode(studentAddress.getZipCode());
			student.setStudentAddress(address);
		}

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
