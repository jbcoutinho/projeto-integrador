package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class State {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String name;

		@ManyToOne
		private Region region;

		public State(String name, Region region) {
				this.region= region;
				this.name = name;
		}
}


