package br.com.quadrilatero.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadrilatero.dtos.CategoriaPostRequestDto;
import br.com.quadrilatero.entities.Categoria;
import br.com.quadrilatero.repositories.CategoriaRepository;


@RestController
@RequestMapping(value = "/api/categorias")
public class CategoriaController {

	@PostMapping
	public String post(@RequestBody CategoriaPostRequestDto dto)throws Exception{
		
		try {
			Categoria categoria = new Categoria();
			
			categoria.setId(UUID.randomUUID());
			categoria.setTipo(dto.getTipo());
			
			CategoriaRepository categoriaRepository = new CategoriaRepository();
			
			categoriaRepository.insertCategoria(categoria);
			
			return "Categoria inserida com sucesso!";
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	@GetMapping
	public List<Categoria> getAll()throws Exception{
		
		CategoriaRepository categoriaRepository = new CategoriaRepository();
		
		return categoriaRepository.getAllCategoria();
	}
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") UUID id)throws Exception{
		try {
			CategoriaRepository categoriaRepository = new CategoriaRepository();
			
			Categoria categoria = categoriaRepository.getByIdCategoria(id);
			
			if (categoria == null){
				
				throw new Exception("Categoria não encontrada!");
				
			}
			
			categoriaRepository.deletaCategoria(categoria);
			
			return "Categoria excluida com sucesso!";
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
	
		
	}
}
