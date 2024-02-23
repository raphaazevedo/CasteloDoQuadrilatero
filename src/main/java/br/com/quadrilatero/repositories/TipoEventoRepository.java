package br.com.quadrilatero.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.quadrilatero.entities.TipoEvento;
import br.com.quadrilatero.factory.ConnectionFactory;

public class TipoEventoRepository {

	public void insereTipoEvento(TipoEvento tipoEvento)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("INSERT INTO tipoevento (id, tipo)values(?, ?)");
		
		statement.setObject(1, tipoEvento.getId());
		statement.setObject(2, tipoEvento.getTipo());
		
		statement.execute();
		
		connection.close();
	}
	public List<TipoEvento> getAllTipoEvento()throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM tipoevento ORDER BY tipo");

		ResultSet resultSet = statement.executeQuery();
		
		List<TipoEvento> tipoEventos = new ArrayList<TipoEvento>();
		
		while(resultSet.next()) {
			
			TipoEvento tipoEvento = new TipoEvento();
			
			tipoEvento.setId(UUID.fromString(resultSet.getString("id")));
			tipoEvento.setTipo(resultSet.getString("tipo"));
			
			tipoEventos.add(tipoEvento);
			
		}
		
		connection.close();
		return tipoEventos;
	}
	public void deletaTipoEvento(TipoEvento tipoEvento)throws Exception{
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("DELETE FROM tipoevento WHERE id = ?");
		
		statement.setObject(1, tipoEvento.getId());
		
		statement.execute();
		
		
		connection.close();
	}
}
