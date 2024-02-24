package br.com.quadrilatero.dtos;

import java.util.UUID;

import lombok.Data;


@Data
public class EventosPutRequestDto {
	
	
	
	private UUID id;
	private String dataEvento;
	private String descricao;
	private UUID tipoEventoId;
}
