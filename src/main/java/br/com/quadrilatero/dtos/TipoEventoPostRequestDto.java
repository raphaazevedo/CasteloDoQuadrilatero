package br.com.quadrilatero.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoEventoPostRequestDto {
	@NotBlank(message = "O campo não pode ser vazio!")
	@Size(max = 50, message = "O campo tipo só pode ter no máximo 50 caracteres!")
	private String tipo;
}
