package br.com.quadrilatero.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
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
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/api/categorias")
public class CategoriaController {

	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid CategoriaPostRequestDto dto)throws Exception{
		
		try {
			Categoria categoria = new Categoria();
			
			categoria.setId(UUID.randomUUID());
			categoria.setTipo(dto.getTipo());
			
			CategoriaRepository categoriaRepository = new CategoriaRepository();
			
			categoriaRepository.insertCategoria(categoria);
			
			return ResponseEntity.status(201).body("Categoria inserida com sucesso!");
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	}
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll()throws Exception{
		try {
			CategoriaRepository categoriaRepository = new CategoriaRepository();
			List<Categoria> categorias = categoriaRepository.getAllCategoria();
			
			if(categorias.size() == 0) {
				
				return ResponseEntity.status(204).body(null);
			}
					
			return ResponseEntity.status(200).body(categorias);
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id)throws Exception{
		try {
			CategoriaRepository categoriaRepository = new CategoriaRepository();
			
			Categoria categoria = categoriaRepository.getByIdCategoria(id);
			
			if (categoria == null){
				
				return ResponseEntity.status(204).body("Categoria não encontrada!");
				
			}
			
			categoriaRepository.deletaCategoria(categoria);
			
			return ResponseEntity.status(200).body("Categoria excluida com sucesso!");
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	
		
	}
}
