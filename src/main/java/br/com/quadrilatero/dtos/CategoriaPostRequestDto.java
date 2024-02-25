package br.com.quadrilatero.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaPostRequestDto {
	@NotBlank(message = "O campo tipo não pode ser vazio!")
	@Size(max = 50, message = "O campo tipo só aceita 50 caracteres")
	private String tipo;
}
