package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;

public class PedidoDao {
	

	Connection cn = null;
	PreparedStatement stmt;
	ResultSet rs = null;
	
	public Pedido incluirPedido(Pedido pedido) throws Exception { 
		
		try {
			cn=DaoFactory.criarConexao();
			
			String sql="INSERT INTO pedidos (IDCLIENTE,DATA,DESCRICAO,VALOR) VALUES (?,?,?,?)";
			stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, pedido.getIdCliente());
			stmt.setDate(2, new Date(pedido.getData().getTime()));
			stmt.setString(3, pedido.getDescricao());
			stmt.setDouble(4, pedido.getValor());
			stmt.execute();
			
			rs = stmt.getGeneratedKeys();
			if (rs.next()){
				pedido.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			throw e;
		}
		finally{
			cn.close();
			if (stmt != null) stmt.close();
			if (rs != null) rs.close();
 		}
		return pedido;
	}

	public void atualizarPedido(Pedido pedido) throws Exception { 
		
		try {
			cn=DaoFactory.criarConexao();
			
			String sql="UPDATE pedidos SET IDCLIENTE = ?, DATA = ?, DESCRICAO = ?, VALOR = ? WHERE IDPEDIDO = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, pedido.getIdCliente());
			stmt.setDate(2, new Date(pedido.getData().getTime()));
			stmt.setString(3, pedido.getDescricao());
			stmt.setDouble(4, pedido.getValor());
			stmt.setInt(5, pedido.getId());
			stmt.execute();			
		} catch (Exception e) {
			throw e;
		}
		finally{
			cn.close();
			if (stmt != null) stmt.close();
			if (rs != null) rs.close();
 		}
	}

	public void excluirPedido(Pedido pedido) throws Exception {
		excluirPedido(pedido.getId());
	}

	public void excluirPedido(int id) throws Exception { 
		
		try {
			cn=DaoFactory.criarConexao();
			
			String sql="DELETE FROM pedidos WHERE IDPEDIDO = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (Exception e) {
			throw e;
		}
		finally{
			cn.close();
			if (stmt != null) stmt.close();
			if (rs != null) rs.close();
 		}
	}
	
	public Pedido buscarPedido(int id) throws Exception{ 

		Pedido pedido = null;

		try {
			cn=DaoFactory.criarConexao();
			String sql="SELECT IDCLIENTE, DATA, DESCRICAO, VALOR FROM pedidos WHERE IDPEDIDO=?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()){
				pedido = new Pedido(id, rs.getDate("DATA"), rs.getString("DESCRICAO"), rs.getDouble("VALOR"), rs.getInt("IDCLIENTE"));
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

		return pedido;

	}
	
	public List<Pedido> listarPedidos() throws Exception{ 

		List<Pedido> pedidos = new ArrayList<>();

		try {
			cn=DaoFactory.criarConexao();
			String sql="SELECT IDPEDIDO, IDCLIENTE, DATA, DESCRICAO, VALOR FROM pedidos ORDER BY DATA";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()){
				pedidos.add(new Pedido(rs.getInt("IDPEDIDO"), rs.getDate("DATA"), rs.getString("DESCRICAO"), rs.getDouble("VALOR"), rs.getInt("IDCLIENTE")));
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

		return pedidos;
	}

	public List<Pedido> listarPedidos(Cliente cliente) throws Exception{
		return listarPedidos(cliente.getId());
	}
	
	public List<Pedido> listarPedidos(int idCliente) throws Exception{ 

		List<Pedido> pedidos = new ArrayList<>();

		try {
			cn=DaoFactory.criarConexao();
			String sql="SELECT IDPEDIDO, IDCLIENTE, DATA, DESCRICAO, VALOR FROM pedidos WHERE IDCLIENTE = ? ORDER BY DATA";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			rs = stmt.executeQuery();
			while (rs.next()){
				pedidos.add(new Pedido(rs.getInt("IDPEDIDO"), rs.getDate("DATA"), rs.getString("DESCRICAO"), rs.getDouble("VALOR"), rs.getInt("IDCLIENTE")));
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

		return pedidos;
	}

}