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

import br.com.quadrilatero.dtos.TipoEventoPostRequestDto;
import br.com.quadrilatero.entities.TipoEvento;
import br.com.quadrilatero.repositories.TipoEventoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/tiposEvento")
public class TipoEventoController {

	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid TipoEventoPostRequestDto dto) throws Exception {

		try {
			TipoEvento tipoEvento = new TipoEvento();

			tipoEvento.setId(UUID.randomUUID());
			tipoEvento.setTipo(dto.getTipo());

			TipoEventoRepository tipoEventoRepository = new TipoEventoRepository();

			tipoEventoRepository.insereTipoEvento(tipoEvento);
			
			return ResponseEntity.status(201).body("Tipo de evento cadastrado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}

	}

	@GetMapping
	public ResponseEntity<List<TipoEvento>> getAll() throws Exception {

		try {
			TipoEventoRepository tipoEventoRepository = new TipoEventoRepository();
	
			List<TipoEvento> tipoEventos = tipoEventoRepository.getAllTipoEvento();
			
			if (tipoEventos.size() == 0) {
				return ResponseEntity.status(204).body(null);
			}
			
			return ResponseEntity.status(200).body(tipoEventos);
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}

	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) throws Exception {
		
		
		try {
			
			TipoEventoRepository tipoEventoRepository = new TipoEventoRepository();
			
			TipoEvento tipoEvento = tipoEventoRepository.getByIdTipoEvento(id);
			
			if(tipoEvento == null) {
				return ResponseEntity.status(204).body("Tipo de evento não encontrado!");
			}
			tipoEventoRepository.deletaTipoEvento(tipoEvento);
			
			return ResponseEntity.status(200).body("Tipo de evento excluido com sucesso!");
			
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		

	}
}
