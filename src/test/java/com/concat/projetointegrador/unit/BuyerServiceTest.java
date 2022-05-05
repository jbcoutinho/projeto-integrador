package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.dto.AddressCreateDTO;
import com.concat.projetointegrador.dto.AddressDTO;
import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.*;
import com.concat.projetointegrador.repository.BuyerRepository;
import com.concat.projetointegrador.service.AddressService;
import com.concat.projetointegrador.service.BuyerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BuyerServiceTest {

		private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		private final BuyerRepository buyerRepository = mock(BuyerRepository.class);
		private final AddressService addressService = mock(AddressService.class);
		private final BuyerService buyerService = new BuyerService(buyerRepository, passwordEncoder, addressService);

		private Buyer mockBuyer() {
				return new Buyer(1L, "AnyName", "AnyLastName", "U", "S", 11111111L, new ArrayList<>());
		}

		@Test
		void shouldReturnABuyerById() {
				when(buyerRepository.findById(anyLong())).thenReturn(Optional.of(mockBuyer()));
				Buyer result = buyerService.findById(1L);
				Buyer actual = mockBuyer();

				assertEquals(actual.getClass(), result.getClass());
		}

		@Test
		void shouldAddUserAddress() {
				when(buyerRepository.findById(anyLong())).thenReturn(Optional.of(mockBuyer()));
				when(addressService.create(any(), any(), any(), any(), any())).thenReturn(addressMock());
				Mockito.when(buyerRepository.save(Mockito.any())).thenReturn(mockBuyer());
				Buyer buyer = buyerService.addAddress(1L, mockAddressCreateDTO());

				assertEquals(buyer.getAddresses().size(), 0);
		}

		@Test
		void shouldReturnExceptionWhenBuyerDoesntExist() {
				EntityNotFound exception = assertThrows(EntityNotFound.class, () -> buyerService.findById(1L));
				String expectedMessage = "Comprador não encontrado";
				String actualMessage = exception.getMessage();

				assertEquals(actualMessage, expectedMessage);
		}

		@Test
		void shouldCreateBuyer() {
				Buyer buyer;
				Mockito.when(buyerRepository.findByCpf(Mockito.anyLong())).thenReturn(Optional.empty());
				Mockito.when(buyerRepository.save(Mockito.any())).thenReturn(mockBuyer());
				buyer = buyerService.create(mockBuyer());
				assertEquals("U", buyer.getName());
		}

		@Test
		void shouldReturnBuyerAlreadyRegistered() {
				Mockito.when(buyerRepository.findByCpf(Mockito.anyLong())).thenReturn(Optional.of(mockBuyer()));
				Mockito.when(buyerRepository.save(Mockito.any())).thenReturn(mockBuyer());
				Throwable exception = assertThrows(RuntimeException.class, () -> buyerService.create(mockBuyer()));
				assertEquals("Comprador já cadastrado", exception.getMessage());
		}

		private AddressCreateDTO mockAddressCreateDTO() {
				return AddressCreateDTO.builder()
								.name("dadada")
								.build();
		}

		private Address addressMock() {
				return Address
								.builder()
								.id(1L)
								.identifier("casa")
								.street("Rua")
								.number(0)
								.neighborhood("neighborhood")
								.zip("zip")
								.addOn("addOn")
								.state(stateMock())
								.user(userMock())
								.build();
		}

		private User userMock() {
				return Buyer.builder()
								.id(1L)
								.build();
		}

		private State stateMock() {
				return new State("RJ", regionMock());
		}

		private Region regionMock() {
				return Region
								.builder()
								.value(BigDecimal.valueOf(20))
								.build();
		}

}