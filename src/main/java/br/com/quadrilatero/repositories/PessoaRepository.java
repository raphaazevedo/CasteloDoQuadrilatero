package br.com.quadrilatero.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.quadrilatero.entities.Categoria;
import br.com.quadrilatero.entities.Pessoa;
import br.com.quadrilatero.factory.ConnectionFactory;

public class PessoaRepository {
	
	public void inserePessoa(Pessoa pessoa)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("INSERT INTO pessoa (id, nome, apelido, email, nascimento, categoria_id) Values (?,?,?,?,?,?)");
		
		statement.setObject(1, pessoa.getId());
		statement.setObject(2, pessoa.getNome());
		statement.setObject(3, pessoa.getApelido());
		statement.setObject(4, pessoa.getEmail());
		statement.setObject(5, pessoa.getNascimento());
		statement.setObject(6, pessoa.getCategoria().getId());
		
		
		statement.execute();
		connection.close();
	}
	public void deletaPessoa(Pessoa pessoa)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("DELETE FROM pessoa WHERE id = ?");
		
		statement.setObject(1, pessoa.getId());
		
		statement.execute();		
		
		connection.close();
	}
	public List<Pessoa> getAllPessoa()throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM pessoa ORDER BY nome");
		
		ResultSet resultSet = statement.executeQuery();
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		while (resultSet.next()) {
			
			Pessoa pessoa = new Pessoa();
			pessoa.setCategoria(new Categoria());
			
			pessoa.setId(UUID.fromString(resultSet.getString("id")));
			pessoa.setNome(resultSet.getString("nome"));
			pessoa.setApelido(resultSet.getString("apelido"));
			pessoa.setNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("nascimento")));
			pessoa.setEmail(resultSet.getString("email"));
			pessoa.getCategoria().setId(UUID.fromString(resultSet.getString("categoria_id")));
			
			pessoas.add(pessoa);
			
		}		
		
		connection.close();
		return pessoas;
		
	}
	public Pessoa getByIdPessoa(UUID id)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM pessoa WHERE id = ?");

		statement.setObject(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		Pessoa pessoa = null;
		
		if(resultSet.next()) {
			
			pessoa = new Pessoa();
			
			pessoa.setId(UUID.fromString(resultSet.getString("id")));
			pessoa.setNome(resultSet.getString("nome"));
			pessoa.setApelido(resultSet.getString("apelido"));
			pessoa.setNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("nascimento")));
			pessoa.setEmail(resultSet.getString("email"));
			pessoa.getCategoria().setId(UUID.fromString(resultSet.getString("categoria_id")));
			
		}
		
		connection.close();
		return pessoa;
		
	}
	public void atualizaPessoa(Pessoa pessoa)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("UPDATE pessoa SET apelido = ?, email = ? WHERE id = ?");
		
		statement.setObject(1, pessoa.getApelido());
		statement.setObject(2, pessoa.getEmail());
		statement.setObject(3, pessoa.getId());
		statement.execute();


		connection.close();
	}
}
