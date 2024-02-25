package br.com.quadrilatero.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class EventosPostRequestDto {
	
	@NotBlank(message = "O campo nome não pode ser vazio!")
	@Size(max = 150, message = "O nome deve ter até 150 caractere")
	private String nome;
	@NotBlank(message = "O campo data não pode ser vazio!")
	@Pattern(regexp = "\\d{2}\\/\\d{2}\\/\\d{4}", message = "O campo data deve ter o padrão: 99/99/9999!")
	private String dataEvento;
	@NotBlank(message = "O campo descrição não pode ser vazio!")
	private String descricao;
	@NotNull(message = "Insira um id válido")
	private UUID tipoEventoID;
}
