package aplicacao;

import Fachada.Fachada;

public class Atualizar {
	
	public Atualizar() {
		Fachada.inicializar();
		
		System.out.println("========== ATUALIZAR ==========");

		try {
			
			Fachada.alterarEnderecoCliente("Joao Silva", "rua qualquer 123");
			
			Fachada.alterarPrecoServico("banho maior", 44.0);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Fachada.finalizar();
			System.out.println("Fim do programa");
		}
		
		
	}
	
	public static void main(String[] args) {
		new Atualizar();
	}

}
