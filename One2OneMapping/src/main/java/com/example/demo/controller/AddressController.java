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

import com.example.demo.dto.AddressDTO;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.service.AddressService;

@RestController
@RequestMapping("/address/v1")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping("/")
	public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO) {

		AddressDTO response = addressService.addAddress(addressDTO);

		return new ResponseEntity<AddressDTO>(response, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<AddressDTO>> getAllAddress() {

		List<AddressDTO> response = addressService.getAllAddress();

		return new ResponseEntity<List<AddressDTO>>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressDTO> getAddressById(@PathVariable("id") Integer addressId) {

		AddressDTO response=null;
		try {
			response = addressService.getAddressById(addressId);
		} catch (AddressNotFoundException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<AddressDTO>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AddressDTO> updateAddress(@PathVariable("id") Integer addressId,
			@RequestBody AddressDTO addressDTO) {

		AddressDTO response = null;
		try {
			response = addressService.updateAddress(addressId, addressDTO);
		} catch (AddressNotFoundException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<AddressDTO>(response, HttpStatus.ACCEPTED);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<AddressDTO> partialUpdateAddress(@PathVariable("id") Integer addressId,
			@RequestBody Map<String, Object> addressDTO) {

		AddressDTO response = null;
		try {
			response = addressService.partialUpdateAddress(addressId, addressDTO);
		} catch (AddressNotFoundException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<AddressDTO>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAddressById(@PathVariable("id") Integer addressId) throws AddressNotFoundException {

		boolean response = addressService.deleteAddressById(addressId);

		if (!response) {

			throw new AddressNotFoundException("Address Not Found with given:" + addressId);

		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
