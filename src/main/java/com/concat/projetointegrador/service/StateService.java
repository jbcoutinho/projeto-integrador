package com.concat.projetointegrador.service;

import com.concat.projetointegrador.model.State;
import com.concat.projetointegrador.model.Region;
import com.concat.projetointegrador.repository.RegionRepository;
import com.concat.projetointegrador.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StateService {

		private StateRepository repository;
		private RegionRepository regionRepository;

		public State findByName(String name) {
				Optional<State> optState = repository.findByName(name);
				if(optState.isPresent()){
						return optState.get();
				}
				List<Region> regions = regionRepository.findAll();
				List<Region> collect = regions.stream().filter(r -> r.getStates().contains(name)).collect(Collectors.toList());

				if(collect.size() > 1 || collect.size()<=0){
						throw new RuntimeException("Falha ao validar regiao :(");
				}

				return new State(name, collect.get(0));
		}

}
