package com.concat.projetointegrador.repository;

import com.concat.projetointegrador.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateRepository extends JpaRepository<State, Long> {
		Optional<State> findByName(String name);
}
