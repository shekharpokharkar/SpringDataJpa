package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.AddressDTO;
import com.example.demo.exception.AddressNotFoundException;

public interface AddressService {

	public boolean deleteAddressById(Integer addressId);

	public AddressDTO partialUpdateAddress(Integer addressId, Map<String, Object> addressDTO) throws AddressNotFoundException;

	public AddressDTO updateAddress(Integer addressId, AddressDTO addressDTO) throws AddressNotFoundException;

	public AddressDTO getAddressById(Integer addressId) throws AddressNotFoundException;

	public List<AddressDTO> getAllAddress();

	public AddressDTO addAddress(AddressDTO addressDTO);

}
