package br.com.quadrilatero.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadrilatero.dtos.EventosPostRequestDto;
import br.com.quadrilatero.entities.Evento;
import br.com.quadrilatero.entities.TipoEvento;
import br.com.quadrilatero.repositories.EventosRepository;
import br.com.quadrilatero.repositories.TipoEventoRepository;

@RestController
@RequestMapping(value = "/api/eventos")
public class EventoController {

	@PostMapping
	public String post(@RequestBody EventosPostRequestDto dto)throws Exception{
		
		try {
			Evento evento = new Evento();
			
			evento.setId(UUID.randomUUID());
			evento.setNome(dto.getNome());
			evento.setDescricao(dto.getDescricao());
			evento.setDataEvento(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDataEvento()));
			
			TipoEventoRepository tipoEventoRepository = new TipoEventoRepository();
			
			TipoEvento tipoEvento = tipoEventoRepository.getByIdTipoEvento(dto.getTipoEventoID());
			
			if (tipoEvento == null) {
				return "Tipo de evento não encontrado";
			}
			
			evento.setTipoEvento(tipoEvento);
			
			EventosRepository eventosRepository = new EventosRepository();
			
			eventosRepository.InsereEvento(evento);
			
			return "Evento cadastrado com sucesso!";
		} catch (Exception e) {
				return e.getMessage();
		}
		
	}

	@DeleteMapping
	public void delete() throws Exception {

	}

	@PutMapping
	public void put() throws Exception {

	}

	@GetMapping
	public List<Evento> getAll() throws Exception {
		EventosRepository eventosRepository = new EventosRepository();

		return eventosRepository.getAllEventos();
	}

	@GetMapping("{id}")
	public Evento getById(@PathVariable("id") UUID id) throws Exception {

		EventosRepository eventosRepository = new EventosRepository();

		return eventosRepository.getByIdEvento(id);

	}
}