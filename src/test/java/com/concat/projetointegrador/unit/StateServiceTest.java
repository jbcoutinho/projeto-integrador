package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.configuration.AppConfig;
import com.concat.projetointegrador.dto.AddressDTO;
import com.concat.projetointegrador.model.*;
import com.concat.projetointegrador.repository.AddressRepository;
import com.concat.projetointegrador.repository.RegionRepository;
import com.concat.projetointegrador.repository.StateRepository;
import com.concat.projetointegrador.service.AddressService;
import com.concat.projetointegrador.service.StateService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StateServiceTest {

    @Mock
    private static StateRepository stateRepository = Mockito.mock(StateRepository.class);

    @Mock
    private static RegionRepository regionRepository = Mockito.mock(RegionRepository.class);

    private static StateService stateService;

    @BeforeAll
    public static void init() {
        stateService = new StateService(stateRepository, regionRepository);
    }

    @Test
    void shouldReturnAState() {
        Mockito
            .when(regionRepository.findAll())
            .thenReturn(new ArrayList<>());
        Mockito
            .when(stateRepository.findByName(Mockito.any()))
            .thenReturn(Optional.of(new State(1L,"",new Region())));

        State state = stateService.findByName("RJ");

        assertEquals(state.getId(),1);
    }

}
