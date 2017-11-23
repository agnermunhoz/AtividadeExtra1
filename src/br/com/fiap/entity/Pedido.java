package br.com.fiap.entity;

import java.util.Date;

public class Pedido {
	
	private int id;
	private int idCliente;
	private Date data;
	private String descricao;
	private double valor;
	
	public Pedido(){
		
	}

	public Pedido(int id, Date data, String descricao, double valor, int idCliente) {
		super();
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
		this.id=id;
		this.idCliente=idCliente;
	}
	public Pedido(Date data, String descricao, double valor, int idCliente) {
		super();
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
		this.idCliente=idCliente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", idCliente=" + idCliente + ", data=" + data + ", descricao=" + descricao
				+ ", valor=" + valor + "]";
	}

}