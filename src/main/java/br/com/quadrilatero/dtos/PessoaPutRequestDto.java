package br.com.quadrilatero.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class PessoaPutRequestDto {
	private UUID id;
	
	@NotBlank(message = "O apelido não pode ser vazio!")
	@Size(max = 30, message = "O apelido só pode ter até 30 caracteres!")
	private String apelido;
	@Email(message = "Insira um e-mail válido")
	@NotBlank(message = "O capo e-mail não pode ser vazio!")
	private String email;
	
}
