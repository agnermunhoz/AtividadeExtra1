package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;

public class ClienteDao {

	Connection cn = null;
	PreparedStatement stmt;
	ResultSet rs = null;

	public Cliente inserirCliente(Cliente cliente) throws Exception{

		try {
			cn = DaoFactory.criarConexao();
			stmt = cn.prepareStatement("INSERT INTO clientes (NOME, EMAIL) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cliente.getNome()); 
			stmt.setString(2, cliente.getEmail());
			stmt.executeUpdate();
			
			rs = stmt.getGeneratedKeys();
			while (rs.next()){
				cliente.setId(rs.getInt(1));
			}
		} catch (Exception e) { 
			throw e;
		} finally { 
			cn.close();
			if (stmt != null) stmt.close();
		} 
		return cliente;
	}
	
	public void atualizarCliente(Cliente cliente) throws Exception{

		try {
			cn = DaoFactory.criarConexao();
			stmt = cn.prepareStatement("UPDATE clientes SET NOME = ?, EMAIL = ? WHERE IDCLIENTE = ?");
			stmt.setString(1, cliente.getNome()); 
			stmt.setString(2, cliente.getEmail());
			stmt.setInt(3, cliente.getId());
			stmt.executeUpdate();
		} catch (Exception e) { 
			throw e;
		} finally { 
			cn.close();
			if (stmt != null) stmt.close();
		} 
	}

	public void excluirCliente(Cliente cliente) throws Exception{
		excluirCliente(cliente.getId());
	}
	
	public void excluirCliente(int id) throws Exception{

		try {
			cn = DaoFactory.criarConexao();
			stmt = cn.prepareStatement("DELETE FROM clientes WHERE IDCLIENTE = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) { 
			throw e;
		} finally { 
			cn.close();
			if (stmt != null) stmt.close();
		} 
	}

	public Cliente buscarCliente(int id) throws Exception{ 

		Cliente cliente = null;
		
		try {
			cn=DaoFactory.criarConexao();
			String sql="SELECT NOME,EMAIL FROM clientes WHERE IDCLIENTE=?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()){
				cliente = new Cliente(id, rs.getString("NOME"), rs.getString("EMAIL"));
			}

		} catch (Exception e) {
			throw e;
		}
		finally{
			cn.close();
			stmt.close();
			if (stmt != null) stmt.close();
			if (rs != null) rs.close();
		}

		return cliente;

	} 
	
	public List<Cliente> listarClientes() throws Exception{ 

		List<Cliente> clientes = new ArrayList<>();
		
		try {
			cn=DaoFactory.criarConexao();
			String sql="SELECT IDCLIENTE,NOME,EMAIL FROM clientes ORDER BY NOME";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while  (rs.next()){
				clientes.add(new Cliente(rs.getInt("IDCLIENTE"), rs.getString("NOME"), rs.getString("EMAIL")));
			}

		} catch (Exception e) {
			throw e;
		}
		finally{
			cn.close();
			stmt.close();
			if (stmt != null) stmt.close();
			if (rs != null) rs.close();
		}

		return clientes;

	} 
}