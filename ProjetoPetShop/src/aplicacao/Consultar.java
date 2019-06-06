package aplicacao;

import Fachada.Fachada;

public class Consultar {
	
public Consultar() {
		
		System.out.println("========== CONSULTAS ==========");
		try {
			
			Fachada.inicializar();
			
			// Consulta cliente
			System.out.println(Fachada.consultarClientePorParteNome("Lucas"));
			
			// Consulta telefone
			System.out.println(Fachada.consultarClientePorTelefone("(83) 98800-0000"));
			
			// Consultar animal do cliente
			System.out.println(Fachada.consultarAnimaisDoCliente("Maria Silva"));
			System.out.println(Fachada.consultarAnimaisDoCliente("Renatha Victor"));
			System.out.println("\n");

			// Consultar cliente do animal
			System.out.println(Fachada.consultarClienteDoAnimal("Zoe"));
			System.out.println(Fachada.consultarClienteDoAnimal("Bob"));
			System.out.println(Fachada.consultarClienteDoAnimal("Pandhora"));
			System.out.println(Fachada.consultarClienteDoAnimal("Smelly cat"));
			System.out.println("\n");
			
			// Consultar valor do atendimento
			System.out.println(Fachada.consultarValorAtendimento(1));
			System.out.println(Fachada.consultarValorAtendimento(2));
			System.out.println(Fachada.consultarValorAtendimento(3));
			System.out.println(Fachada.consultarValorAtendimento(4));
			System.out.println("\n");
	
			//animais que consumiram mais produtos por atendimento
			System.out.println(Fachada.consultarClienteMaisConsumiu());
			System.out.println("\n");
			
			//clientes que compraram o produto tal
			System.out.println(Fachada.consultarClientesPorProduto("racao pedigree equilibrio natural 1kg"));
			
			
			//clientes que compraram o servico tal
			System.out.println(Fachada.consultarClientesPorServico("banho maior"));				
			
			// servicos do animal
			System.out.println(Fachada.consultarServicoAnimal("Zoe"));
			
			// clientes que fizerem pedidos com mais de dois produtos
			System.out.println(Fachada.consultarAtendimentoQuantidadeProdutos(2));
			
			System.out.println(Fachada.consultarDonoERacaAnimal("bob"));
					
			// as 3 consultas
			System.out.println(" === AS TRES CONSULTAS === ");
			System.out.println(Fachada.consultarClientesQueTenhamCompradoProdutoEServico("bravecto", "banho maior"));
			
			System.out.println(Fachada.consultarClientesPorServicoOuProduto("banho maior"));
			
			System.out.println(Fachada.consultarRacaConsumiuProduto("bravecto", "banho menor"));

			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Fachada.finalizar();
		}
		
		System.out.println("fim do programa");

	}
	
	public void Consultar(){

	}	
	
	public static void main(String[] args) {
		new Consultar();
	}

}
