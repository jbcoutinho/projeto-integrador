package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateDTO {
		@JsonProperty("cep")
		private String zip;
		@JsonProperty("numero")
		private Integer number;
		@JsonProperty("complemento")
		private String addOn;
		@JsonProperty("nome")
		private String name;


		public static AddressCreateDTO map(Address address){
				return AddressCreateDTO
								.builder()
								.zip(address.getZip())
								.addOn(address.getAddOn())
								.number(address.getNumber())
								.name(address.getIdentifier())
								.build();
		}
}
