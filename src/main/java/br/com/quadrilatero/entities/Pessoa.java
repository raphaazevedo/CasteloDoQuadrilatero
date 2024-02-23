package br.com.quadrilatero.entities;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class Pessoa {
	private UUID id;
	private String nome;
	private String apelido;
	private String email;
	private Date nascimento;
	private Categoria categoria;
}
