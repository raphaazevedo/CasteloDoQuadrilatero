package br.com.quadrilatero.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.quadrilatero.entities.DepoimentoFesta;
import br.com.quadrilatero.entities.Evento;
import br.com.quadrilatero.factory.ConnectionFactory;

public class DepoimentoFestaRepository {
	
	public void insereDepoimentoFesta(DepoimentoFesta depoimentoFesta)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("INSERT INTO depoimento_festa (id, escritor, depoimento, festa_id) VALUES (?, ?, ?, ?)");
		
		statement.setObject(1, depoimentoFesta.getId());
		statement.setObject(2, depoimentoFesta.getEscritor());
		statement.setObject(3, depoimentoFesta.getDepoimento());
		statement.setObject(4, depoimentoFesta.getEvento().getId());
		
		statement.execute();
		
		connection.close();
		
	}
	public DepoimentoFesta getByIdDepoimentoFesta(UUID id)throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM depoimento_festa WHERE id = ?");
		statement.setObject(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		DepoimentoFesta depoimentoFesta = null;
		
		if(resultSet.next()) {
			depoimentoFesta = new DepoimentoFesta();
			depoimentoFesta.setEvento(new Evento());
			
			depoimentoFesta.setId(UUID.fromString(resultSet.getString("id")));
			depoimentoFesta.setEscritor(resultSet.getString("escritor"));
			depoimentoFesta.setDepoimento(resultSet.getString("depoimento"));
			depoimentoFesta.getEvento().setId(UUID.fromString(resultSet.getString("festa_id")));
			
			
		}
		
		
		connection.close();
		return depoimentoFesta;
		
	}
	public List<DepoimentoFesta> getAllDepoimentoFesta()throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM depoimento_festa");
		
		ResultSet resultSet = statement.executeQuery();
		
		List<DepoimentoFesta> depoimentoFestas = new ArrayList<DepoimentoFesta>();
		
		while(resultSet.next()) {
			
			DepoimentoFesta depoimentoFesta = new DepoimentoFesta();
			depoimentoFesta.setEvento(new Evento());
			
			depoimentoFesta.setId(UUID.fromString(resultSet.getString("id")));
			depoimentoFesta.setEscritor(resultSet.getString("escritor"));
			depoimentoFesta.setDepoimento(resultSet.getString("depoimento"));
			depoimentoFesta.getEvento().setId(UUID.fromString(resultSet.getString("festa_id")));
			
			depoimentoFestas.add(depoimentoFesta);
			
		}
		
		connection.close();
		return depoimentoFestas;
	}

	public void deletaDepoimentoFesta(DepoimentoFesta depoimentoFesta)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("DELETE FROM depoimento_festa WHERE id = ?");
		
		statement.setObject(1, depoimentoFesta.getId());
		
		statement.execute();		
		
		connection.close();
		
	}
}
