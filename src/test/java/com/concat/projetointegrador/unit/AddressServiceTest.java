package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.configuration.AppConfig;
import com.concat.projetointegrador.dto.AddressDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.*;
import com.concat.projetointegrador.repository.AddressRepository;
import com.concat.projetointegrador.repository.BatchStockRepository;
import com.concat.projetointegrador.repository.InboundOrderRepository;
import com.concat.projetointegrador.service.AddressService;
import com.concat.projetointegrador.service.BuyerService;
import com.concat.projetointegrador.service.InboundOrderService;
import com.concat.projetointegrador.service.StateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressServiceTest {

    @Mock
    private static StateService stateService = Mockito.mock(StateService.class);

    @Mock
    private static AddressRepository addressRepository = Mockito.mock(AddressRepository.class);

    @Mock
    private static BuyerService buyerService = Mockito.mock(BuyerService.class);

    @Mock
    private static RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    private static AddressService addressService;

    @BeforeAll
    public static void init() {
        addressService = new AddressService(addressRepository, stateService, restTemplate, new AppConfig());
    }

    @Test
    void shouldCreateAInboundOrder() {
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(addressMock());
        Mockito
            .when(restTemplate.getForObject(
                    Mockito.anyString(),
                    ArgumentMatchers.any(Class.class)))
            .thenReturn(mockAddressDTO());
        Mockito
            .when(stateService.findByName(Mockito.anyString()))
            .thenReturn(stateMock());
        addressService.create("25900028", 10, "", "", new User());

        assertEquals(addresses.size() ,1);
    }

    @Test
    void shouldReturnAListOfAddresses() {
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(addressMock());
        Mockito
            .when(addressRepository.findByUserId(1L))
            .thenReturn(addresses);

        List<Address> byUserId = addressService.findByUserId(1L);

        assertEquals(addresses.size() ,byUserId.size());
    }

    @Test
    void shouldReturnTheShippingFee() {
        Mockito
            .when(addressRepository.findByUserIdAndId(1L,1L))
            .thenReturn(addressMock());

        BigDecimal bigDecimal = addressService.calculateShippingFee(1L, 1L);
        assertEquals(BigDecimal.valueOf(20) ,bigDecimal);
    }

    private AddressDTO mockAddressDTO() {
        return AddressDTO.builder()
                .state("RJ")
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
