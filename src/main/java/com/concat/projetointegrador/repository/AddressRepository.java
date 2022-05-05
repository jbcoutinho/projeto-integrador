package com.concat.projetointegrador.repository;

import com.concat.projetointegrador.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
		List<Address> findByUserId(Long userId);

		Address findByUserIdAndId(Long userId, Long addressId);
}
