package br.com.quadrilatero.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadrilatero.dtos.TipoEventoPostRequestDto;
import br.com.quadrilatero.entities.TipoEvento;
import br.com.quadrilatero.repositories.TipoEventoRepository;

@RestController
@RequestMapping(value = "/api/tiposEvento")
public class TipoEventoController {

	@PostMapping
	public String post(@RequestBody TipoEventoPostRequestDto dto) throws Exception {

		try {
			TipoEvento tipoEvento = new TipoEvento();

			tipoEvento.setId(UUID.randomUUID());
			tipoEvento.setTipo(dto.getTipo());

			TipoEventoRepository tipoEventoRepository = new TipoEventoRepository();

			tipoEventoRepository.insereTipoEvento(tipoEvento);
			
			return "Tipo de evento cadastrado com sucesso";
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@GetMapping
	public List<TipoEvento> getAll() throws Exception {

		TipoEventoRepository tipoEventoRepository = new TipoEventoRepository();

		return tipoEventoRepository.getAllTipoEvento();

	}

	@DeleteMapping
	public void delete() throws Exception {

	}
}
