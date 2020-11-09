package com.eva.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eva.event.RecursoCriadoEvent;
import com.eva.exceptionhandler.EvaExceptionHandler.Erro;
import com.eva.model.Arte;
import com.eva.repository.ArteRepository;
import com.eva.service.ArteService;
import com.eva.service.exception.ArteExistente;

@RestController
@RequestMapping("/artes")
public class ArteResource {

	@Autowired
	private ArteRepository arteRepository;
	
	@Autowired
	private ArteService arteService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
		
	@GetMapping
	public Page<Arte> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
		return arteRepository.findAll(pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Arte> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Arte> arte = arteRepository.findById(codigo);
		return arte != null ? ResponseEntity.ok(arte.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ARTE')")
	public ResponseEntity<Arte> criar(@Valid @RequestBody Arte arte, HttpServletResponse response) {
		Arte arteSalva = arteService.salvar(arte);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, arteSalva.getIdArte()));
		return ResponseEntity.status(HttpStatus.CREATED).body(arteSalva);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ARTE')")
	public ResponseEntity<Arte> atualizar(@PathVariable Long codigo, @Valid @RequestBody Arte arte) {
		try {
			Arte lancamentoSalvo = arteService.atualizar(codigo, arte);
			return ResponseEntity.ok(lancamentoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_EXCLUIR_ARTE')")
	public void remover(@PathVariable Long codigo) {
		arteRepository.deleteById(codigo);
	}
	
	@ExceptionHandler({ ArteExistente.class })
	public ResponseEntity<Object> handleArteExistente(ArteExistente ex) {
		String mensagemUsuario = messageSource.getMessage("arte.ja.existente", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
}