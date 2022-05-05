package com.concat.projetointegrador.integration;

import com.concat.projetointegrador.dto.InboundOrderDTO;
import com.concat.projetointegrador.dto.ShippingFeeDTO;
import com.concat.projetointegrador.model.InboundOrder;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(value = {"/inbound-order-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AddressControllerTest {

		@Autowired
		private MockMvc mock;

		@Test
		public void shouldCreateAInboundOrder() throws Exception {
				SimpleGrantedAuthority supervisor = new SimpleGrantedAuthority("Supervisor");
				ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
				simpleGrantedAuthorities.add(supervisor);
				MvcResult result = mock.perform(get("/address/{userId}", 1L)
												.with(
																user("Supervisor")
																.password("123")
																.authorities(simpleGrantedAuthorities)
												)
								)
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andReturn();

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

				String jsonReturned = result.getResponse().getContentAsString();
				List list = objectMapper.readValue(jsonReturned, List.class);

				assertEquals(0, list.size());

		}

		@Test
		public void shouldCreateAShippingFee() throws Exception {
				SimpleGrantedAuthority supervisor = new SimpleGrantedAuthority("Supervisor");
				ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
				simpleGrantedAuthorities.add(supervisor);
				MvcResult result = mock.perform(get("/user/{userId}/address/{addressId}", 4, 1)
												.with(
																user("Supervisor")
																.password("123")
																.authorities(simpleGrantedAuthorities)
												)
								)
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andReturn();

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

				String jsonReturned = result.getResponse().getContentAsString();

				assertEquals("{\"taxaDeEnvio\":20.00}", jsonReturned);

		}


}


