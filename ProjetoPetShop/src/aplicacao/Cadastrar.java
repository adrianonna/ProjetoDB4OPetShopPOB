package aplicacao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import Fachada.Fachada;
import modelo.Animal;
import modelo.Atendimento;
import modelo.Cliente;
import modelo.Produto;
import modelo.Raca;
import modelo.Servico;

public class Cadastrar {
	
	
	public Cadastrar() {
		
		System.out.println("========== CADASTRO ==========");
		try {
			
			Fachada.inicializar();
			
			// cadastro raças
			Fachada.cadastrarRaca("rottweiler", "grande", "cachorro");
			Fachada.cadastrarRaca("border collie", "medio", "cachorro");
			Fachada.cadastrarRaca("boxer", "grande", "cachorro");
			Fachada.cadastrarRaca("pug", "pequeno", "cachorro");
			Fachada.cadastrarRaca("persa", "pequeno", "gato");
			
			
			
			// cadastro produtos
			Fachada.cadastrarProduto("bravecto", 150.0);
			Fachada.cadastrarProduto("racao pedigree equilibrio natural 3kg", 45.0);
			Fachada.cadastrarProduto("racao pedigree equilibrio natural 1kg", 20.0);
			Fachada.cadastrarProduto("shampoo anti-pulgas", 18.0);
			Fachada.cadastrarProduto("brinquedo anti-stress de borracha", 10.0);
			Fachada.cadastrarProduto("osso grande", 25.0);
			
			
			
			// cadastro serviços
			Fachada.cadastrarServico("banho e tosa menor", 48.0);
			Fachada.cadastrarServico("banho e tosa medio", 60.0);
			Fachada.cadastrarServico("banho e tosa maior", 80.0);
			Fachada.cadastrarServico("banho menor", 20.0);
			Fachada.cadastrarServico("banho medio", 35.0);
			Fachada.cadastrarServico("banho maior", 46.0);
			Fachada.cadastrarServico("corte de unhas", 10.0);
			Fachada.cadastrarServico("hospedagem", 50.0);
			
			
			
			// cadastro clientes
			Fachada.cadastrarCliente("Renatha Victor", "(83) 98800-0000");
			Fachada.cadastrarCliente("Adriano Amaral", "(83) 98800-0001");
			Fachada.cadastrarCliente("Adriano Ney", "(83) 98800-0002");
			Fachada.cadastrarCliente("Joao Silva", "rua sem nome, 45, mangabeira", "(83) 98811-0000", "joaosilva@email.com");
			Fachada.cadastrarCliente("Maria Silva", "rua qualquer, 145, bancarios", "(83) 98001-0000", "mariasilva@email.com");

			
			
			// cadastro animais
			Fachada.cadastrarAnimal("Zoe", "border collie");
			Fachada.cadastrarAnimal("Bob", "boxer");
			Fachada.cadastrarAnimal("Pandhora", "rottweiler");
			Fachada.cadastrarAnimal("Smelly cat", "persa");


			
			// adicionar animal ao cliente
			Fachada.adicionarAnimalDoCliente("Zoe", "Renatha Victor");
			Fachada.adicionarAnimalDoCliente("Bob", "Joao Silva");
			Fachada.adicionarAnimalDoCliente("Smelly cat", "Maria Silva");
			Fachada.adicionarAnimalDoCliente("Pandhora", "Maria Silva");


			
			// criar atendimento
			Fachada.cadastrarAtendimento(LocalDate.now(), "Funcionario1", "Zoe");
			Fachada.cadastrarAtendimento(LocalDate.now(), "Funcionario1", "Bob");
			Fachada.cadastrarAtendimento(LocalDate.now(), "Funcionario2", "Pandhora");
			Fachada.cadastrarAtendimento(LocalDate.now(), "Funcionario3", "Smelly cat");
					
			
			
			// adicionar produto ao atendimento
			Fachada.adicionarProdutoAtendimento(1, "bravecto");
			Fachada.adicionarProdutoAtendimento(1, "brinquedo anti-stress de borracha");
			Fachada.adicionarProdutoAtendimento(1, "shampoo anti-pulgas");
			Fachada.adicionarProdutoAtendimento(1, "racao pedigree equilibrio natural 1kg");
			
			Fachada.adicionarProdutoAtendimento(2, "bravecto");
			Fachada.adicionarProdutoAtendimento(2, "shampoo anti-pulgas");
			
			Fachada.adicionarProdutoAtendimento(3, "bravecto");
			Fachada.adicionarProdutoAtendimento(3, "shampoo anti-pulgas");
			Fachada.adicionarProdutoAtendimento(3, "racao pedigree equilibrio natural 1kg");
			
			Fachada.adicionarProdutoAtendimento(4, "bravecto");
			Fachada.adicionarProdutoAtendimento(4, "racao pedigree equilibrio natural 3kg");
			Fachada.adicionarProdutoAtendimento(4, "brinquedo anti-stress de borracha");
			
			
			
			// adicionar serviço ao atendimento
			Fachada.adicionarServicoAtendimento(1, "banho e tosa maior");
			Fachada.adicionarServicoAtendimento(1, "corte de unhas");
			Fachada.adicionarServicoAtendimento(1, "hospedagem");
			Fachada.adicionarServicoAtendimento(2, "banho menor");
			Fachada.adicionarServicoAtendimento(2, "hospedagem");
			Fachada.adicionarServicoAtendimento(3, "banho menor");
			Fachada.adicionarServicoAtendimento(4, "banho maior");
			
			
			
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Fachada.finalizar();
		}
		
		System.out.println("fim do programa");

	}
	
	public void cadastrar(){

	}	
	
	public static void main(String[] args) {
		new Cadastrar();
	}
}
