
package dao;

import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Animal;
import modelo.Atendimento;
import modelo.Cliente;
import modelo.Servico;
import modelo.Produto;


public class DAOAtendimento extends DAO<Atendimento>{
	//Leitura POR id 
	public Atendimento read (Object chave) {
		int cod = (int) chave;
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.descend("cod").constrain(cod);
		List<Atendimento> resultados = q.execute();	
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	// CONSULTAS 
	
	public String consultarClientesQueTenhamCompradoProdutoEServico(String nomeProd, String nomeServ) {
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.descend("produtos").descend("nome").constrain(nomeProd);
		q.descend("servicos").descend("nome").constrain(nomeServ);
		List<Atendimento> resultados = q.execute();	
		String texto = "";
		for (Atendimento atendimento : resultados) {
			texto += atendimento.getAnimal().getCliente().getNome() + " | ";
		}
		return ""+texto;
	}
	
	public String consultarRacaConsumiuProduto(String nomeProduto, String nomeServico) {
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.descend("produtos").descend("nome").constrain(nomeProduto);
		q.descend("servicos").descend("nome").constrain(nomeServico);
		List<Atendimento> resultados = q.execute();
		String texto = "";
		for (Atendimento atendimento : resultados) {
			texto += atendimento.getAnimal().getRaca().getDescricao() + " | ";
		}
		return ""+ texto;
	}

	
	
	public ArrayList<Cliente> consultarClientesPorProduto(String nome) {
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.descend("produtos").descend("nome").constrain(nome);
		List<Atendimento> resultados = q.execute();
		ArrayList<Cliente> clientes = new ArrayList<>();
		for(Atendimento aten : resultados) {
			clientes.add(aten.getAnimal().getCliente()); 
		}
		return clientes;
	}
	
	public ArrayList<Cliente> consultarClientesPorServicoOuProduto(String nome) {
		Query q1 = manager.query();
		Query q2 = manager.query();
		q1.constrain(Atendimento.class);
		q2.constrain(Atendimento.class);
		q1.descend("servicos").descend("nome").constrain(nome);
		q2.descend("produtos").descend("nome").constrain(nome);
		if (!q1.execute().isEmpty()) {
			List<Atendimento> resultados = q1.execute();
			ArrayList<Cliente> clientes = new ArrayList<>();
			for(Atendimento aten : resultados) {
				clientes.add(aten.getAnimal().getCliente()); 
			}
			return clientes;
		}else {
			List<Atendimento> resultados = q2.execute();
			ArrayList<Cliente> clientes = new ArrayList<>();
			for(Atendimento aten : resultados) {
				clientes.add(aten.getAnimal().getCliente()); 
			}
			return clientes;
		}
	}
	
	public double consultarValorAtendimento(int id) {
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.descend("cod").constrain(id);
		List<Atendimento> atendimentos = q.execute();	//retorna uma lista com a quantidade de elementos iguais a consulta
		Atendimento aten = atendimentos.get(0);
		return aten.getPrecoTotal();
	}
	
	public Atendimento consultarClienteMaisConsumiu() {
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.descend("precoTotal").orderDescending();
		List<Atendimento> atendimentos = q.execute();
		return atendimentos.get(0);
	}
	
	public ArrayList<Cliente> consultarClientesPorServico(String nome) {
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.descend("servicos").descend("nome").constrain(nome);
		List<Atendimento> resultados = q.execute();
		ArrayList<Cliente> clientes = new ArrayList();
		for(Atendimento aten : resultados) {
			clientes.add(aten.getAnimal().getCliente()); 
		}
		return clientes;
	}
	
	public List<Atendimento> consultarClientesPorServicoEProduto(String serv, String prod) {
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.descend("servicos").descend("nome").constrain(serv);
		q.descend("produtos").descend("nome").constrain(prod);
		List<Atendimento> resultados = q.execute();
		return resultados;
	}
		
	public int consultarUltimoAtendimento() {
		Query q = manager.query();
		q.constrain(Atendimento.class);
		List<Atendimento> resultados = q.execute();	
		if (resultados.size()>0)
			return resultados.get(resultados.size()-1).getCod();
		else
			return 0;
	}
	
	public List<Atendimento> consultarAtendimentoQuantidadeProdutos(int qtd){
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.constrain(new Filtro(qtd));
		List<Atendimento> result = q.execute();
		return result;

	}

	
}

@SuppressWarnings("serial")
class Filtro  implements Evaluation {
	private int n;
	public Filtro (int n) {this.n=n;}
	public void evaluate(Candidate candidate) {
		Atendimento aten = (Atendimento) candidate.getObject();
		candidate.include(aten.getProdutos().size() >= n);
	}
}
