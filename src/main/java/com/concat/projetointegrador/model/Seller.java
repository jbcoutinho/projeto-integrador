package com.concat.projetointegrador.model;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@DiscriminatorValue("Seller")
public class Seller extends User {

    @Builder
    public Seller(Long id, String username, String password, String name, String lastName, Long cpf, List<Address> addresses) {
        super(id, username, password, name, lastName, cpf, addresses);
    }

}
