package com.eva.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.eva.model.Arte;
import com.eva.repository.ArteRepository;

@Service
public class ArteService { 
	
	@Autowired
	private ArteRepository arteRepository;
	
	public Arte salvar(Arte arte) {
		return arteRepository.save(arte);
	}
	
	public Arte atualizar(Long codigo,  Arte arte) {
		Arte arteSalva = buscarArtePeloCodigo(codigo);
		
		BeanUtils.copyProperties(arte, arteSalva, "codigo");
		return arteRepository.save(arteSalva);
	}
	
	public Arte buscarArtePeloCodigo(Long codigo) {
		Optional<Arte> arteSalva = arteRepository.findById(codigo);
		if (arteSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return arteSalva.get();
	}
}
