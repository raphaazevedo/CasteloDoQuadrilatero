package br.com.quadrilatero.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class DepoimentoFestaPostRequestDto {

	
	private String escritor;
	private String depoimento;
	private UUID eventoId;
}
