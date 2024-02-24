package br.com.quadrilatero.dtos;

import java.util.UUID;

import lombok.Data;


@Data
public class EventosPostRequestDto {
	private String nome;
	private String dataEvento;
	private String descricao;
	private UUID tipoEventoID;
}
