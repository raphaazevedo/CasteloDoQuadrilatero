package br.com.quadrilatero.entities;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class Evento {
	private UUID id;
	private String nome;
	private Date dataEvento;
	private String descricao;
	private TipoEvento tipoEvento;
	
}
