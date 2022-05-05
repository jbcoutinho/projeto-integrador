package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.AddressCreateDTO;
import com.concat.projetointegrador.dto.AddressDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Address;
import com.concat.projetointegrador.model.Buyer;
import com.concat.projetointegrador.repository.BuyerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BuyerService {

		private BuyerRepository buyerRepository;
		private final BCryptPasswordEncoder passwordEncoder;
		private AddressService addressService;

		/**
		 * find a buyer by its id
		 *
		 * @param id Long - buyers id
		 * @return buyer if it exists
		 * @throws EntityNotFound if buyer doenst exist
		 */
		public Buyer findById(Long id) {
				Optional<Buyer> buyer = buyerRepository.findById(id);
				if (buyer.isPresent()) {
						return buyer.get();
				}
				throw new EntityNotFound("Comprador não encontrado");
		}

		/**
		 * create a new buyer
		 *
		 * @param buyer - buyer object to insert
		 * @return a buyer
		 */
		public Buyer create(Buyer buyer) {
				buyer.setPassword(passwordEncoder.encode(buyer.getPassword()));
				Optional<Buyer> buyerOpt = buyerRepository.findByCpf(buyer.getCpf());
				if (buyerOpt.isPresent()) {
						throw new RuntimeException("Comprador já cadastrado");
				}
				return buyerRepository.save(buyer);
		}

		public Buyer addAddress(Long buyerId, AddressCreateDTO addressDTO) {
				//TODO validar usuario no token
				Buyer buyer = findById(buyerId);
				Address address = addressService.create(addressDTO.getZip(), addressDTO.getNumber(), addressDTO.getAddOn(), addressDTO.getName(), buyer);
				buyer.getAddresses().add(address);
				return buyerRepository.save(buyer);
		}
}
