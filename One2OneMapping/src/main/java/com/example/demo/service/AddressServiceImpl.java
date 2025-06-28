package com.example.demo.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.demo.dto.AddressDTO;
import com.example.demo.entity.Address;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.repository.AddressRepository;
import com.example.demo.utility.AddressUtility;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public boolean deleteAddressById(Integer addressId) {

		boolean flag = false;
		Optional<Address> byId = addressRepository.findById(addressId);

		if (byId.get() != null) {
			flag = true;
			addressRepository.delete(byId.get());
		}

		return flag;
	}

	@Override
	public AddressDTO partialUpdateAddress(Integer addressId, Map<String, Object> addressDTO)
			throws AddressNotFoundException {

		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new AddressNotFoundException("Enter addressId is not found:" + addressId));

		addressDTO.forEach((fieldName, fieldValue) -> {

			Field field = ReflectionUtils.findField(Address.class, fieldName);

			if (field != null) {
				field.setAccessible(true);
				ReflectionUtils.setField(field, address, fieldValue);
			}
		});

		Address updatedAddress = addressRepository.save(address);

		AddressDTO updatedaddressDTO = AddressUtility.mapToAddressDTO(updatedAddress);

		return updatedaddressDTO;
	}

	@Override
	public AddressDTO updateAddress(Integer addressId, AddressDTO addressDTO) throws AddressNotFoundException {

		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new AddressNotFoundException("Enter addressId is not found:" + addressId));

		Address updateAddress = AddressUtility.mapToAddress(addressDTO);

		updateAddress.setAddressId(addressId);
		address = updateAddress;

		Address save = addressRepository.save(updateAddress);

		return AddressUtility.mapToAddressDTO(save);
	}

	@Override
	public AddressDTO getAddressById(Integer addressId) throws AddressNotFoundException {

		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new AddressNotFoundException("Enter addressId is not found:" + addressId));

		return AddressUtility.mapToAddressDTO(address);
	}

	@Override
	public List<AddressDTO> getAllAddress() {
		List<AddressDTO> addreeDTOList = new ArrayList<>();
		List<Address> addressList = addressRepository.findAll();

		for (Address address : addressList) {

			addreeDTOList.add(AddressUtility.mapToAddressDTO(address));

		}
		return addreeDTOList;
	}

	@Override
	public AddressDTO addAddress(AddressDTO addressDTO) {

		return AddressUtility.mapToAddressDTO(addressRepository.save(AddressUtility.mapToAddress(addressDTO)));

	}

}
