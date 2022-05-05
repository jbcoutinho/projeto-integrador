package com.concat.projetointegrador.service;

import com.concat.projetointegrador.configuration.AppConfig;
import com.concat.projetointegrador.dto.AddressDTO;
import com.concat.projetointegrador.model.Address;
import com.concat.projetointegrador.model.Buyer;
import com.concat.projetointegrador.model.State;
import com.concat.projetointegrador.model.User;
import com.concat.projetointegrador.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

		private AddressRepository addressRepository;
		private StateService stateService;
		private RestTemplate restTemplate;
		private AppConfig config;

//		public Address getAddress(String zip) {
//				String url = config.getViaCepBaseURL() + zip + config.getViaCepReturnType();
//
//				AddressDTO objectReturnedFromViaCEP =  restTemplate.getForObject(url,AddressDTO.class);
//				State state = stateService.findByName(objectReturnedFromViaCEP.getState(), objectReturnedFromViaCEP.getState());
//
//				return Address.builder()
//								.city(objectReturnedFromViaCEP.getCity())
//								.neighborhood(objectReturnedFromViaCEP.getNeighborhood())
//								.zip(objectReturnedFromViaCEP.getZip())
//								.state(state)
//								.street(objectReturnedFromViaCEP.getStreet())
//								.build();
//		}

		public Address create(String zip, Integer number, String addOn, String name, User user) {
				String url = config.getViaCepBaseURL() + zip + "/" +  config.getViaCepReturnType();

				AddressDTO objectReturnedFromViaCEP = restTemplate.getForObject(url, AddressDTO.class);

				State state = stateService.findByName(objectReturnedFromViaCEP.getState());

				restTemplate.getForObject(url, AddressDTO.class);

				Address address = Address.builder()
								.user(user)
								.city(objectReturnedFromViaCEP.getCity())
								.number(number)
								.neighborhood(objectReturnedFromViaCEP.getNeighborhood())
								.zip(objectReturnedFromViaCEP.getZip())
								.state(state)
								.addOn(addOn)
								.street(objectReturnedFromViaCEP.getStreet())
								.identifier(name)
								.build();

				return addressRepository.save(address);
		}

		public List<Address> findByUserId(Long userId) {
				return addressRepository.findByUserId(userId);
		}

		public BigDecimal calculateShippingFee(Long userId, Long addressId) {
				Address address = addressRepository.findByUserIdAndId(userId, addressId);
				return address.getState().getRegion().getValue();
		}

//		public Address update(Address address, String zip) throws InvalidZipCodeException {
//				String url = config.getViaCepBaseURL() + zip + config.getViaCepReturnType();
//				AddressDTO objectReturnedFromViaCEP = restTemplate.getForObject(url, AddressDTO.class);
//
//				address.setNeighborhood(objectReturnedFromViaCEP.getNeighborhood());
//				address.setZip(objectReturnedFromViaCEP.getZip());
//				address.setCity(objectReturnedFromViaCEP.getCity());
//				address.setState(objectReturnedFromViaCEP.getState());
//				address.setStreet(objectReturnedFromViaCEP.getStreet());
//
//				Address updatedAddress = addressRepository.save(address);
//
//				return updatedAddress;
//		}
}
