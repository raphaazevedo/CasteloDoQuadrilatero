package br.com.quadrilatero.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class EventosPutRequestDto {
	
	
	
	private UUID id;
	@NotBlank(message = "O campo data não pode ser vazio!")
	@Pattern(regexp = "\\d{2}\\/\\d{2}\\/\\d{4}", message = "O campo data deve ter o padrão: 99/99/9999!")
	private String dataEvento;
	@NotBlank(message = "O campo descrição não pode ser vazio!")
	private String descricao;
	
}
