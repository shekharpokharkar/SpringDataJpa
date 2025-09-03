package com.example.demo.utility;

import org.springframework.beans.BeanUtils;

import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;

public interface CourseUtility {

	public static CourseDTO mapToCourseDTO(Course course) {

		CourseDTO courseDTO = new CourseDTO();
		BeanUtils.copyProperties(course, courseDTO);
		if (course.getStudent() != null) {
			Student student = course.getStudent();
			StudentDTO dto = new StudentDTO();
			BeanUtils.copyProperties(student, dto);
			courseDTO.setStudent(dto);
		}
		return courseDTO;
	}

	public static Course mapToCourse(CourseDTO courseDTO) {

		Course course = new Course();
		BeanUtils.copyProperties(courseDTO, course);

		if (courseDTO.getStudent() != null) {
			StudentDTO studentdto = courseDTO.getStudent();
			Student student = new Student();
			BeanUtils.copyProperties(studentdto, student);
			course.setStudent(student);
		}

		return course;
	}

}
