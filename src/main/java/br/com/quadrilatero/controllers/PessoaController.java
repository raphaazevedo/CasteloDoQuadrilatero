package br.com.quadrilatero.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadrilatero.dtos.PessoaPostRequestDto;
import br.com.quadrilatero.dtos.PessoaPutRequestDto;
import br.com.quadrilatero.entities.Categoria;
import br.com.quadrilatero.entities.Pessoa;
import br.com.quadrilatero.repositories.CategoriaRepository;
import br.com.quadrilatero.repositories.PessoaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/pessoas")
public class PessoaController {

	
	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid PessoaPostRequestDto dto)throws Exception{
		
		try {
			
			Pessoa pessoa = new Pessoa();
			
			pessoa.setId(UUID.randomUUID());
			pessoa.setNome(dto.getNome());
			pessoa.setApelido(dto.getApelido());
			pessoa.setEmail(dto.getEmail());
			pessoa.setNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getNascimento()));
			
			CategoriaRepository categoriaRepository = new CategoriaRepository();
			
			Categoria categoria = categoriaRepository.getByIdCategoria(dto.getCategoriaId());
			
			if (categoria == null) {
				return ResponseEntity.status(204).body("Categoria não encontrada");
			}
						
			pessoa.setCategoria(categoria);

			PessoaRepository pessoaRepository = new PessoaRepository();
			
			pessoaRepository.inserePessoa(pessoa);
			
			return ResponseEntity.status(201).body("Pessoa cadastrada com sucesso!");
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id)throws Exception{
		
		try {
		
			PessoaRepository pessoaRepository = new PessoaRepository();
			
			Pessoa pessoa = pessoaRepository.getByIdPessoa(id);
			
			if(pessoa == null) {
				return ResponseEntity.status(204).body("Pessoa não encontrada!");
			}
			
			pessoaRepository.deletaPessoa(pessoa);
			
			return ResponseEntity.status(200).body("Pessoa deletada com sucesso!");
		}catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
		
	}
	@GetMapping
	public ResponseEntity<List<Pessoa>> getAll()throws Exception{
		
		try {
			PessoaRepository pessoaRepository = new PessoaRepository();
			
			List<Pessoa> pessoas = pessoaRepository.getAllPessoa();
			
			if(pessoas.size() == 0) {
				return ResponseEntity.status(204).body(null);
			}
			
			return ResponseEntity.status(200).body(pessoas);
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	@GetMapping("{id}")
	public ResponseEntity<Pessoa> getById(@PathVariable("id") UUID id)throws Exception{
		try {
			PessoaRepository pessoaRepository = new PessoaRepository();
			
			Pessoa pessoa = pessoaRepository.getByIdPessoa(id);
			
			if(pessoa == null) {
				return ResponseEntity.status(204).body(null);
			}
			
			
			return ResponseEntity.status(200).body(pessoa);
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	@PutMapping()
	public ResponseEntity<String> put(@RequestBody @Valid PessoaPutRequestDto dto){
		
		try {
			
			PessoaRepository pessoaRepository = new PessoaRepository();
			
			Pessoa pessoa = pessoaRepository.getByIdPessoa(dto.getId());
			
			if(pessoa == null) {
				return ResponseEntity.status(204).body("Pessoa não encontrada!");
			}
			
			pessoa.setApelido(dto.getApelido());
			pessoa.setEmail(dto.getEmail());
				
			pessoaRepository.atualizaPessoa(pessoa);

			
			return ResponseEntity.status(201).body("Pessoa atualizada com sucesso!");
			
			
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
		
	}
	
	
}
