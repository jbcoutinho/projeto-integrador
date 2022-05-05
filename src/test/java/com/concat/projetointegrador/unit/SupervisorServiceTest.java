package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.model.Buyer;
import com.concat.projetointegrador.model.Supervisor;
import com.concat.projetointegrador.repository.SupervisorRepository;
import com.concat.projetointegrador.service.SupervisorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SupervisorServiceTest {
    @Mock
    private static SupervisorRepository supervisorRepository;

    private static SupervisorService supervisorService;

    private static BCryptPasswordEncoder passwordEncoder;

    @BeforeAll
    static void init() {
        supervisorRepository = Mockito.mock(SupervisorRepository.class);
        supervisorService = new SupervisorService(supervisorRepository);
    }

    @Test
    public void shouldReturnASupervisorWithId() {
        Mockito.when(supervisorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(createSupervisor()));
        Supervisor supervisor = supervisorService.findById(1L);

        Supervisor expectResult = createSupervisor();

        assertEquals(supervisor.getId(), expectResult.getId());
        assertEquals(supervisor.getName(), expectResult.getName());
        assertEquals(supervisor.getLastName(), expectResult.getLastName());
    }

    @Test
    public void shouldReturnErrorWhenNotFoundSupervisor() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> supervisorService.findById(1L));
        assertTrue(thrown.getMessage().contains("Não foi encontrado!"));
    }
    private Supervisor createSupervisor() {
        return Supervisor.builder()
                .id(1L)
                .name("Renato")
                .lastName("Graça")
                .password("123")
                .username("Renatinho")
                .build();
    }

    @Test
    void shouldCreateSupervisor() {
        Supervisor supervisor;
        Mockito.when(supervisorRepository.save(Mockito.any())).thenReturn(createSupervisor());
        supervisor = supervisorService.create(createSupervisor());
        assertEquals("Renato", supervisor.getName());
    }
}
