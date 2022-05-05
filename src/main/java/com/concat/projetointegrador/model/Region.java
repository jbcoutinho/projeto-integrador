package com.concat.projetointegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		private String region;
		@JsonIgnore
		private BigDecimal value;
		@JsonIgnore
		@OneToMany(mappedBy = "region")
		private List<State> states;
}
