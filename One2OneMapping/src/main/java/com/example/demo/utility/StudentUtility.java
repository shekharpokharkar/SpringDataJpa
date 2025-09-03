package com.example.demo.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Address;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;

public interface StudentUtility {

	public static Student mapToStudent(StudentDTO studentDTO) {

		Student student = new Student();

		BeanUtils.copyProperties(studentDTO, student);
		String studentPassword = studentDTO.getStudentPassword();
		student.setStudentPassword(studentPassword.toCharArray());

		if (studentDTO.getStudentAddress() != null) {
			Address address = new Address();
			AddressDTO addressDTO = studentDTO.getStudentAddress();
			BeanUtils.copyProperties(addressDTO, address);
			address.setStudent(student);
			student.setStudentAddress(address);
		}

		if (studentDTO.getCourseDTO() != null) {
			List<Course> course = new ArrayList<>();
			List<CourseDTO> courseDTO = studentDTO.getCourseDTO();
			System.out.println("courseDTO"+courseDTO);
			for (CourseDTO dto : courseDTO) {
				Course course1 = new Course();
				BeanUtils.copyProperties(dto, course1);
				course1.setStudent(student);
				course.add(course1);
				
			}

			student.setCourse(course);
		}

		return student;

	}

	public static StudentDTO mapToStudentDTO(Student student) {

		StudentDTO studentdto = new StudentDTO();

		BeanUtils.copyProperties(student, studentdto);

		if (student.getStudentAddress() != null) {
			AddressDTO addressDTO = new AddressDTO();
			Address address = student.getStudentAddress();
			BeanUtils.copyProperties(address, addressDTO);
			studentdto.setStudentAddress(addressDTO);
		}

		if (student.getCourse() != null) {
			List<Course> course = student.getCourse();
			List<CourseDTO> courseDTO = new ArrayList<>();

			for (Course course1 : course) {
				CourseDTO cou = new CourseDTO();
				BeanUtils.copyProperties(course1, cou);
				courseDTO.add(cou);
			}

			studentdto.setCourseDTO(courseDTO);

		}

		return studentdto;

	}
}
