package br.com.quadrilatero.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadrilatero.dtos.EventosPostRequestDto;
import br.com.quadrilatero.dtos.EventosPutRequestDto;
import br.com.quadrilatero.entities.Evento;
import br.com.quadrilatero.entities.TipoEvento;
import br.com.quadrilatero.repositories.EventosRepository;
import br.com.quadrilatero.repositories.TipoEventoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/eventos")
public class EventoController {

	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid EventosPostRequestDto dto)throws Exception{
		
		try {
			Evento evento = new Evento();
			
			evento.setId(UUID.randomUUID());
			evento.setNome(dto.getNome());
			evento.setDescricao(dto.getDescricao());
			evento.setDataEvento(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDataEvento()));
			
			TipoEventoRepository tipoEventoRepository = new TipoEventoRepository();
			
			TipoEvento tipoEvento = tipoEventoRepository.getByIdTipoEvento(dto.getTipoEventoID());
			
			if (tipoEvento == null) {
				return ResponseEntity.status(204).body("Tipo de evento não encontrado");
			}
			
			evento.setTipoEvento(tipoEvento);
			
			EventosRepository eventosRepository = new EventosRepository();
			
			eventosRepository.InsereEvento(evento);
			
			return ResponseEntity.status(200).body("Evento cadastrado com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) throws Exception {
		
		try {
			EventosRepository eventosRepository = new EventosRepository();
			
			Evento evento = eventosRepository.getByIdEvento(id);
			
			if (evento == null) {
				return ResponseEntity.status(204).body("Evento não encontrado!!");
			}
			
			eventosRepository.deletaEvento(evento);
			
			return ResponseEntity.status(200).body("Evento deletado com sucesso!");
			
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	}

	@PutMapping()
	public ResponseEntity<String> put(@RequestBody @Valid EventosPutRequestDto dto) throws Exception {
		
		try {
			
			EventosRepository eventosRepository = new EventosRepository();
			
			Evento evento = eventosRepository.getByIdEvento(dto.getId());
			
			if (evento == null) {
				return ResponseEntity.status(204).body("Evento não encontrado!");
			}
			
			evento.setDataEvento(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDataEvento()));
			evento.setDescricao(dto.getDescricao());
			
			
			
			eventosRepository.updateEvento(evento);
			
			return ResponseEntity.status(200).body("Evento atualizado com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}

	}

	@GetMapping
	public ResponseEntity<List<Evento>> getAll() throws Exception {
		
		try {
			EventosRepository eventosRepository = new EventosRepository();
	
			List<Evento> eventos = eventosRepository.getAllEventos();
			
			return ResponseEntity.status(200).body(eventos);
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Evento> getById(@PathVariable("id") UUID id) throws Exception {

		try {
			EventosRepository eventosRepository = new EventosRepository();
			Evento evento = eventosRepository.getByIdEvento(id);
			
			if (evento == null) {
				return ResponseEntity.status(204).body(null);
			}
			
			return ResponseEntity.status(200).body(evento);
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
}
