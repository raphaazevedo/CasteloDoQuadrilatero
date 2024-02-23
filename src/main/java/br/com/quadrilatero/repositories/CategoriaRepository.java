package br.com.quadrilatero.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.quadrilatero.entities.Categoria;
import br.com.quadrilatero.factory.ConnectionFactory;

public class CategoriaRepository {
	
	public void insertCategoria(Categoria categoria)throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("INSERT INTO categoria (id, tipo) VALUES (?, ?)");
		
		statement.setObject(1, categoria.getId());
		statement.setObject(2, categoria.getTipo());
		statement.execute();
		
		connection.close();
		
	}
	public List<Categoria> getAllCategoria()throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM categoria ORDER BY tipo");
		ResultSet resultSet = statement.executeQuery();
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		while(resultSet.next()) {
			
			Categoria categoria = new Categoria();
			
			categoria.setId(UUID.fromString(resultSet.getString("id")));
			categoria.setTipo(resultSet.getString("tipo"));
			
			categorias.add(categoria);
			
		}
		
		connection.close();
		return categorias;
	}
	public void deletaCategoria(Categoria categoria)throws Exception{
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("DELETE FROM categoria WHERE id = ?");
		
		statement.setObject(1, categoria.getId());
		
		statement.execute();
		
		connection.close();
		
	}
}
