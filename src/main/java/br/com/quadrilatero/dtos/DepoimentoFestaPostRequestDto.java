package br.com.quadrilatero.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepoimentoFestaPostRequestDto {

	@Size(max = 150, message = "O campo nome pode ter até 150 caracteres!")
	@NotBlank(message = "O campo nome não pode ser vazio!")
	private String escritor;
	@NotBlank(message = "O campo depoimento não pode ser vazio!")
	private String depoimento;
	@NotNull(message = "Insira um id válido")
	private UUID eventoId;
}
