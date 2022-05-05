package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Buyer;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyerDTO {
		private Long id;
		private String name;
		private String lastName;
		private String username;
		private Long cpf;
		private List<AddressDTO> addresses;

		public static BuyerDTO convertToBuyerDTO(Buyer buyer) {
				return BuyerDTO
								.builder()
								.id(buyer.getId())
								.name(buyer.getName())
								.lastName(buyer.getLastName())
								.username(buyer.getUsername())
								.cpf(buyer.getCpf())
								.addresses(buyer.getAddresses().stream().map(e->AddressDTO.map(e)).collect(Collectors.toList()))
								.build();
		}
}

