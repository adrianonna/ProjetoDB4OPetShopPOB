package aplicacao;

import Fachada.Fachada;

public class Listar {
	
public Listar() {
		
		System.out.println("========== LISTAS ==========");
		try {
			
			Fachada.inicializar();
			
			// listar racas
			System.out.println(Fachada.listarRaca());
			
			// listar produtos
			System.out.println(Fachada.listarProdutos());
			
			// listar servicos
			System.out.println(Fachada.listarServicos());
			
			// listar clientes
			System.out.println(Fachada.listarClientes());
			
			// listar animais
			System.out.println(Fachada.listarAnimais());
						
			// listar atendimento
			System.out.println(Fachada.listarAtendimento());
			
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Fachada.finalizar();
		}
		
		System.out.println("fim do programa");

	}
	
	public void listar(){

	}	
	
	public static void main(String[] args) {
		new Listar();
	}

}
