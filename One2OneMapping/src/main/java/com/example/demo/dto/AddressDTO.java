package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class AddressDTO {
	private Integer addressId;
	private String laneName1;
	private String laneName2;
	private String city;
	private String state;
	private String zipCode;

	@JsonIgnore
	private StudentDTO studentDTO;
}
