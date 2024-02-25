package br.com.quadrilatero.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

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

@RestController
@RequestMapping(value = "/api/pessoas")
public class PessoaController {

	
	@PostMapping
	public String post(@RequestBody PessoaPostRequestDto dto)throws Exception{
		
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
				return "Categoria não encontrada";
			}
						
			pessoa.setCategoria(categoria);

			PessoaRepository pessoaRepository = new PessoaRepository();
			
			pessoaRepository.inserePessoa(pessoa);
			
			return "Pessoa cadastrada com sucesso!";
			
		}catch (Exception e) {
			return e.getMessage();
		}
		
	}
	@DeleteMapping
	public void delete()throws Exception{
		
	}
	@GetMapping
	public List<Pessoa> getAll()throws Exception{
		
		PessoaRepository pessoaRepository = new PessoaRepository();
		
		return pessoaRepository.getAllPessoa();
	}
	@GetMapping("{id}")
	public Pessoa getById(@PathVariable("id") UUID id)throws Exception{
		
		PessoaRepository pessoaRepository = new PessoaRepository();
		
		return pessoaRepository.getByIdPessoa(id);
	}
	@PutMapping()
	public String put(@RequestBody PessoaPutRequestDto dto){
		
		try {
			
			PessoaRepository pessoaRepository = new PessoaRepository();
			
			Pessoa pessoa = pessoaRepository.getByIdPessoa(dto.getId());
			
			if(pessoa == null) {
				throw new Exception("Pessoa não encontrada!");
			}
			
			pessoa.setApelido(dto.getApelido());
			pessoa.setEmail(dto.getEmail());
				
			pessoaRepository.atualizaPessoa(pessoa);

			
			return "Pessoa atualizada com sucesso!";
			
			
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
		
	}
	
	
}
