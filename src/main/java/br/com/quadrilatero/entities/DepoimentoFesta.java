package br.com.quadrilatero.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class DepoimentoFesta {
	private UUID id;
	private String escritor;
	private String depoimento;
	private Evento evento;
}
