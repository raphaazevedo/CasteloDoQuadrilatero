package br.com.quadrilatero.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class Categoria {
	private UUID id;
	private String tipo;
}
