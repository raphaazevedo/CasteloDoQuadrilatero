package br.com.quadrilatero.dtos;

import java.util.UUID;

import lombok.Data;



@Data
public class PessoaPostRequestDto {
	private String nome;
	private String apelido;
	private String email;
	private String nascimento;
	private UUID categoriaId;
}
