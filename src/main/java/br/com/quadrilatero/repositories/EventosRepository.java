package br.com.quadrilatero.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.quadrilatero.entities.Evento;
import br.com.quadrilatero.entities.TipoEvento;
import br.com.quadrilatero.factory.ConnectionFactory;

public class EventosRepository {
	
	public void InsereEvento (Evento evento)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("INSERT INTO eventos (id, nome, dataevento, tipoevento_id, descricao) VALUES (?,?,?,?,?)");
		
		statement.setObject(1, evento.getId());
		statement.setObject(2, evento.getNome());
		statement.setObject(3, new java.sql.Date(evento.getDataEvento().getTime()));
		statement.setObject(4, evento.getTipoEvento().getId());
		statement.setObject(5, evento.getDescricao());

		statement.execute();
		connection.close();
	}
	public void deletaEvento (Evento evento)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("DELETE FROM eventos WHERE id=?");
		
		statement.setObject(1, evento.getId());
		
		statement.execute();		
		
		connection.close();
	}
	public void updateEvento (Evento evento)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("UPDATE eventos SET dataevento = ?, descricao = ? WHERE id = ?");
		
		statement.setObject(1, new java.sql.Date(evento.getDataEvento().getTime()));

		statement.setObject(2, evento.getDescricao());
		statement.setObject(3, evento.getId());
		
		statement.execute();

		connection.close();
	}
	public List<Evento> getAllEventos ()throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				//("SELECT * FROM eventos");
				("select d.id, d.nome, d.dataevento, d.tipoevento_id, d.descricao, e.tipo "
						+ " from eventos d inner join tipoevento e "
						+ " on d.tipoevento_id = e.id");
		
		ResultSet resultSet = statement.executeQuery();
		
		List<Evento> eventos = new ArrayList<Evento>();
		
		Evento evento = null;
		
		while(resultSet.next()) {
			
			evento = new Evento();
			evento.setTipoEvento(new TipoEvento());
			
			evento.setId(UUID.fromString(resultSet.getString("id")));
			evento.setNome(resultSet.getString("nome"));
			evento.setDataEvento(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("dataevento")));
			evento.setDescricao(resultSet.getString("descricao"));
			evento.getTipoEvento().setId(UUID.fromString(resultSet.getString("tipoevento_id")));
			evento.getTipoEvento().setTipo(resultSet.getString("tipo"));
			
			eventos.add(evento);
			
		}
		
		
		
		connection.close();
		return eventos;
		
	}
	public Evento getByIdEvento(UUID id)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				//("SELECT * FROM eventos WHERE id = ?");
				("select d.id, d.nome, d.dataevento, d.tipoevento_id, d.descricao, e.tipo "
						+ " from eventos d inner join tipoevento e "
						+ " on d.tipoevento_id = e.id"
						+ " where d.id = ?");
		
		statement.setObject(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		Evento evento = null;
		
		if (resultSet.next()) {
			
			evento = new Evento();
			evento.setTipoEvento(new TipoEvento());
			
			evento.setId(UUID.fromString(resultSet.getString("id")));
			evento.setNome(resultSet.getString("nome"));
			evento.setDataEvento(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("dataevento")));
			evento.setDescricao(resultSet.getString("descricao"));
			evento.getTipoEvento().setId(UUID.fromString(resultSet.getString("tipoevento_id")));
			evento.getTipoEvento().setTipo(resultSet.getString("tipo"));
			
			
		}
		
		
		
		connection.close();
		return evento;
		
	}
}
