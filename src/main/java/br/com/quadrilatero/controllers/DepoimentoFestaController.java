package br.com.quadrilatero.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadrilatero.dtos.DepoimentoFestaPostRequestDto;
import br.com.quadrilatero.entities.DepoimentoFesta;
import br.com.quadrilatero.entities.Evento;
import br.com.quadrilatero.repositories.DepoimentoFestaRepository;
import br.com.quadrilatero.repositories.EventosRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/depoimentosFesta")
public class DepoimentoFestaController {

	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid DepoimentoFestaPostRequestDto dto)throws Exception{
		
		try {
			
			DepoimentoFesta depoimentoFesta = new DepoimentoFesta();
			
			depoimentoFesta.setId(UUID.randomUUID());
			depoimentoFesta.setDepoimento(dto.getDepoimento());
			depoimentoFesta.setEscritor(dto.getEscritor());

			EventosRepository eventosRepository = new EventosRepository();
			Evento evento = eventosRepository.getByIdEvento(dto.getEventoId());
			
			if (evento == null) {
				return ResponseEntity.status(204).body("Evento não encontrado!");
			}
			depoimentoFesta.setEvento(evento);
			
			DepoimentoFestaRepository depoimentoFestaRepository = new DepoimentoFestaRepository();
			
			depoimentoFestaRepository.insereDepoimentoFesta(depoimentoFesta);
			
			return ResponseEntity.status(200).body("Depoimento gravado com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}

		
		
		
	}
	@GetMapping
	public ResponseEntity<List<DepoimentoFesta>> getAll()throws Exception{
		
		try {
			
			DepoimentoFestaRepository depoimentoFestaRepository = new DepoimentoFestaRepository();
			
			List<DepoimentoFesta> depoimentoFestas = depoimentoFestaRepository.getAllDepoimentoFesta();
			
			return ResponseEntity.status(200).body(depoimentoFestas);
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id)throws Exception{
		
		try {
			DepoimentoFestaRepository depoimentoFestaRepository = new DepoimentoFestaRepository();
			
			DepoimentoFesta depoimentoFesta = depoimentoFestaRepository.getByIdDepoimentoFesta(id);
			
			if (depoimentoFesta == null) {
				return ResponseEntity.status(204).body("Depoimento não encontrado!");
			}
			
			depoimentoFestaRepository.deletaDepoimentoFesta(depoimentoFesta);
			
			return ResponseEntity.status(200).body("Depoimento deletado com sucesso!");
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	}
}
