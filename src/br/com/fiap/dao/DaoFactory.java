package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoFactory {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://192.168.99.100:3306/vendas";

	public static DaoFactory getDaoFactory(){ 
		return new DaoFactory();
	} 
	
	public static Connection criarConexao() throws Exception{ 
		return DriverManager.getConnection(URL,"root","root");
	}

	public ClienteDao getClienteDao() {
		return new ClienteDao();
	}

	public PedidoDao getPedidoDao() {
		return new PedidoDao();
	}
}