package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.AddressDTO;
import com.concat.projetointegrador.dto.ShippingFeeDTO;
import com.concat.projetointegrador.model.Address;
import com.concat.projetointegrador.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AddressController {

		private AddressService addressService;

		@GetMapping("/address/{userId}")
		public ResponseEntity<List<Address>> getAllEmployees(@PathVariable Long userId) {
				return new ResponseEntity<>(addressService.findByUserId(userId), HttpStatus.OK);
		}

		@GetMapping("/user/{userId}/address/{addressId}")
		public ResponseEntity<ShippingFeeDTO> getAllEmployees(@PathVariable Long userId, @PathVariable Long addressId) {
				return new ResponseEntity<>(
								ShippingFeeDTO
										.builder()
												.shippingFee(addressService.calculateShippingFee(userId, addressId))
										.build()
								, HttpStatus.OK);
		}
}
