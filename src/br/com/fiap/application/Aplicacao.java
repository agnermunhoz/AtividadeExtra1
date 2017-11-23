package br.com.fiap.application;

import java.util.Date;
import java.util.Scanner;

import br.com.fiap.dao.DaoFactory;
import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;

public class Aplicacao {

	public static int menu() {
		Scanner in = new Scanner(System.in); 
	    System.out.println("*************************");
	    System.out.println("*         MENU          *");
	    System.out.println("* 1 - Incluir Cliente   *");
	    System.out.println("* 2 - Alterar Cliente   *");
	    System.out.println("* 3 - Excluir Cliente   *");
	    System.out.println("* 4 - Pesquisar Cliente *");
	    System.out.println("* 5 - Incluir Pedido    *");
	    System.out.println("* 6 - Alterar Pedido    *");
	    System.out.println("* 7 - Excluir Pedido    *");
	    System.out.println("* 8 - Pesquisar Pedidos *");
	    System.out.println("* 0 - Sair              *");
	    System.out.println("*************************");
	    
	    return in.nextInt();
	}
	
	public static void incluirCliente() {
		Scanner in = new Scanner(System.in); 
	    Cliente cliente = new Cliente();
	    System.out.println("Nome do cliente:");
	    cliente.setNome(in.next());
	    System.out.println("E-mail do cliente:");
	    cliente.setEmail(in.next());
	    try {
			DaoFactory.getDaoFactory().getClienteDao().inserirCliente(cliente);
			System.out.println(cliente.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Cliente pesquisarCliente() {
		Scanner in = new Scanner(System.in);
		System.out.println("Id do cliente:");
		int id = in.nextInt();
		try {
			Cliente cliente = DaoFactory.getDaoFactory().getClienteDao().buscarCliente(id);
			System.out.println(cliente.toString());
			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void atualizarCliente() {
		Scanner in = new Scanner(System.in); 
	    Cliente cliente = pesquisarCliente();
	    System.out.println("Nome do cliente:");
	    cliente.setNome(in.next());
	    System.out.println("E-mail do cliente:");
	    cliente.setEmail(in.next());
	    try {
			DaoFactory.getDaoFactory().getClienteDao().atualizarCliente(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void excluirCliente() {
	    Cliente cliente = pesquisarCliente();
	    try {
			DaoFactory.getDaoFactory().getClienteDao().excluirCliente(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void incluirPedido() {
		Scanner in = new Scanner(System.in); 
	    Cliente cliente = pesquisarCliente();
	    Pedido pedido = new Pedido();
	    pedido.setIdCliente(cliente.getId());
	    pedido.setData(new Date());
	    System.out.println("Descri��o do pedido:");
	    pedido.setDescricao(in.next());
	    System.out.println("Valor do pedido:");
	    pedido.setValor(in.nextDouble());
	    try {
			DaoFactory.getDaoFactory().getPedidoDao().incluirPedido(pedido);
			System.out.println(pedido.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void atualizarPedido() {
		Scanner in = new Scanner(System.in); 
		Pedido pedido = pesquisarPedido();
	    Cliente cliente = pesquisarCliente();
	    pedido.setIdCliente(cliente.getId());
	    pedido.setData(new Date());
	    System.out.println("Descri��o do pedido:");
	    pedido.setDescricao(in.next());
	    System.out.println("Valor do pedido:");
	    pedido.setValor(in.nextDouble());
	    try {
			DaoFactory.getDaoFactory().getPedidoDao().atualizarPedido(pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void excluirPedido() {
		Pedido pedido = pesquisarPedido();
	    try {
			DaoFactory.getDaoFactory().getPedidoDao().excluirPedido(pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Pedido pesquisarPedido() {
		Scanner in = new Scanner(System.in);
		System.out.println("Id do pedido:");
		int id = in.nextInt();
		try {
			Pedido pedido = DaoFactory.getDaoFactory().getPedidoDao().buscarPedido(id);
			System.out.println(pedido.toString());
			return pedido;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void main(String[] args) {
		int option = 0;
		do {
			option = menu();
			switch (option) {
			case 1:
				incluirCliente();
				break;
			case 2:
				atualizarCliente();
				break;
			case 3:
				excluirCliente();
				break;
			case 4:
				pesquisarCliente();
				break;
			case 5:
				incluirPedido();
				break;
			case 6:
				atualizarPedido();
				break;
			case 7:
				excluirPedido();
				break;
			case 8:
				pesquisarPedido();
				break;
			default:
				System.out.println("Finalizando aplicativo!!!");
				break;
			}
		} while (option != 0);
	}

}
