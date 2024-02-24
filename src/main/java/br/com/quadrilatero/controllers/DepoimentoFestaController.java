package br.com.quadrilatero.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadrilatero.dtos.DepoimentoFestaPostRequestDto;
import br.com.quadrilatero.entities.DepoimentoFesta;
import br.com.quadrilatero.entities.Evento;
import br.com.quadrilatero.repositories.DepoimentoFestaRepository;
import br.com.quadrilatero.repositories.EventosRepository;

@RestController
@RequestMapping(value = "/api/depoimentosFesta")
public class DepoimentoFestaController {

	@PostMapping
	public String post(@RequestBody DepoimentoFestaPostRequestDto dto)throws Exception{
		
		try {
			
			DepoimentoFesta depoimentoFesta = new DepoimentoFesta();
			
			depoimentoFesta.setId(UUID.randomUUID());
			depoimentoFesta.setDepoimento(dto.getDepoimento());
			depoimentoFesta.setEscritor(dto.getEscritor());

			EventosRepository eventosRepository = new EventosRepository();
			Evento evento = eventosRepository.getByIdEvento(dto.getEventoId());
			
			if (evento == null) {
				return "Evento não encontrado!";
			}
			depoimentoFesta.setEvento(evento);
			
			DepoimentoFestaRepository depoimentoFestaRepository = new DepoimentoFestaRepository();
			
			depoimentoFestaRepository.insereDepoimentoFesta(depoimentoFesta);
			
			return "Depoimento gravado com sucesso!";
		} catch (Exception e) {
			return e.getMessage();
		}

		
		
		
	}
	@GetMapping
	public List<DepoimentoFesta> getAll()throws Exception{
		
		DepoimentoFestaRepository depoimentoFestaRepository = new DepoimentoFestaRepository();
		
		
		return depoimentoFestaRepository.getAllDepoimentoFesta();
		
	}
	@DeleteMapping
	public void delete()throws Exception{
		
	}
}
