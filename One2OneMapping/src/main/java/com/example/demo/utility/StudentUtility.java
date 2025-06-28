package com.example.demo.utility;

import org.springframework.beans.BeanUtils;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Address;
import com.example.demo.entity.Student;

public class StudentUtility {

	public static Student mapToStudent(StudentDTO studentDTO) {

		Student student = new Student();

		BeanUtils.copyProperties(studentDTO, student);
		String studentPassword = studentDTO.getStudentPassword();
		student.setStudentPassword(studentPassword.toCharArray());

		if (studentDTO.getStudentAddress() != null) {
			Address address = new Address();
			AddressDTO addressDTO = studentDTO.getStudentAddress();
			BeanUtils.copyProperties(addressDTO, address);
			student.setStudentAddress(address);
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

		return studentdto;

	}
}
