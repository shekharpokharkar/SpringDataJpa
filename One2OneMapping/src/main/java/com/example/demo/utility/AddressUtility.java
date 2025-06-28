package com.example.demo.utility;

import org.springframework.beans.BeanUtils;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Address;
import com.example.demo.entity.Student;

public class AddressUtility {

	public static Address mapToAddress(AddressDTO addressDTO) {

		Address address = new Address();

		BeanUtils.copyProperties(addressDTO, address);

		if (addressDTO.getStudentDTO() != null) {
			Student student = new Student();
			StudentDTO studentDTO = addressDTO.getStudentDTO();
			BeanUtils.copyProperties(studentDTO, student);
			address.setStudent(student);
		}

		return address;

	}

	public static AddressDTO mapToAddressDTO(Address address) {

		AddressDTO addressDTO = new AddressDTO();

		BeanUtils.copyProperties(address, addressDTO);

		if (addressDTO.getStudentDTO() != null) {
			StudentDTO studentDTO = new StudentDTO();
			StudentDTO student = addressDTO.getStudentDTO();
			BeanUtils.copyProperties(student, studentDTO);
			addressDTO.setStudentDTO(studentDTO);
		}

		return addressDTO;

	}
}
