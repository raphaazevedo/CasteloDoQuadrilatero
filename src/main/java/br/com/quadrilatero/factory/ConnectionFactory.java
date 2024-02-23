package br.com.quadrilatero.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static String driver = "org.postgresql.Driver";
	private static String url = "jdbc:postgresql://localhost:5432/bd_quadrilatero";
	private static String user = "postgres";
	private static String password = "coti";

	public static Connection getConnection() throws Exception {
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}
}
