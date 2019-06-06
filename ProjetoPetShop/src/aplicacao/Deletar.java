package aplicacao;

import Fachada.Fachada;

public class Deletar {
	
	public Deletar() {
		Fachada.inicializar();
		
		System.out.println("========== DELETAR ==========");
		try {
			System.out.println(Fachada.excluirCliente("Adriano Ney"));
			
			System.out.println(Fachada.excluirProduto("racao pedigree equilibrio natural 3kg"));
			
			System.out.println(Fachada.excluirServico("banho e tosa medio"));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Fachada.finalizar();
			System.out.println("Fim do programa");
		}
	}
	
	public static void main(String[] args) {
		new Deletar();
	}

}
