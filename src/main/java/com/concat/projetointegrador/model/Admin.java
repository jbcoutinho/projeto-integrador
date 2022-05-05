package com.concat.projetointegrador.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;


@Entity
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends User{
		@Builder
		public Admin(Long id, String username, String password, String name, String lastName, Long cpf, List<Address> addresses) {
				super(id, username, password, name, lastName, cpf, addresses);
		}
}
