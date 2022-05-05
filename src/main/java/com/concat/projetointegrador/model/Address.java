package com.concat.projetointegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotNull
		private String identifier;

		@NotNull
		@Column(name="CITY")
		private String city;

		@NotNull
		@Column(name="STREET")
		private String street;

		@NotNull
		@Column(name="NUMBER")
		private Integer number;

		@NotNull
		@Column(name="NEIGHBORHOOD")
		private String neighborhood;

		@NotNull
		@Column(name="ZIP")
		private String zip;

		@Column(name="ADD_ON")
		private String addOn;

		@NotNull
		@ManyToOne
		private State state;

		@ManyToOne
		@JsonIgnore
		private User user;

}
