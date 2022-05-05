package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
		@JsonProperty("cep")
		String zip;

		@JsonProperty("logradouro")
		String street;

		@JsonProperty("bairro")
		String neighborhood;

		@JsonProperty("localidade")
		String city;

		@JsonProperty("uf")
		String state;

		public static AddressDTO map(Address address){
				return AddressDTO
								.builder()
								.zip(address.getZip())
								.neighborhood(address.getNeighborhood())
								.state(address.getState().getName())
								.street(address.getStreet())
								.city(address.getCity())
								.build();
		}
}
