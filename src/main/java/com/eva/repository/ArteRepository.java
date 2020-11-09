package com.eva.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eva.model.Arte;

public interface ArteRepository extends JpaRepository<Arte, Long>{
	
	public Page<Arte> findByNomeContaining(String nome, Pageable pageable);

}
