package br.com.quadrilatero.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaPostRequestDto {
	@NotBlank(message = "O nome não pode ser vazio!")
	@Size(max = 150, message = "O nome deve ter até 150 caracteres")
	private String nome;
	@NotBlank(message = "O apelido não pode ser vazio!")
	@Size(max = 30, message = "O apelido só pode ter até 30 caracteres!")
	private String apelido;
	@Email(message = "Insira um e-mail válido")
	@NotBlank(message = "O capo e-mail não pode ser vazio!")
	private String email;
	@NotBlank(message = "O campo data não pode ser vazio!")
	@Pattern(regexp = "\\d{2}\\/\\d{2}\\/\\d{4}", message = "O campo data deve ter o padrão: 99/99/9999!")
	private String nascimento;
	@NotNull(message = "Insira um id válido")
	private UUID categoriaId;
}
