package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;


@Entity
@NoArgsConstructor
@DiscriminatorValue("Supervisor")
public class Supervisor extends User {
		@Builder
		public Supervisor(Long id, String username, String password, String name, String lastName, Long cpf, List<Address> addresses) {
				super(id, username, password, name, lastName, cpf, addresses);
		}
}
