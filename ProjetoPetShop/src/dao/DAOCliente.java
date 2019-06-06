package dao;

import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import Fachada.Fachada;
import modelo.Animal;
import modelo.Atendimento;
import modelo.Cliente;
import modelo.Produto;
import modelo.Servico;




public class DAOCliente  extends DAO<Cliente> {
	
	
	
	//Leitura POR nome 
	public Cliente read (Object chave) {
		String nome = (String) chave;
		Query q = manager.query();
		q.constrain(Cliente.class);
		q.descend("nome").constrain(nome);
		List<Cliente> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	
	
	//CONSULTAS
	
	public  String consultarClientePorParteNome(String caracteres) {
		Query q = manager.query();
		q.constrain(Cliente.class);
		q.descend("nome").constrain(caracteres).like();
		List<Cliente> result = q.execute(); 
		
		String texto = "\nConsultar cliente por parte do nome: "+caracteres;
		if (result.isEmpty())  
			texto += "\nConsulta vazia";
		else 
			for(Cliente c: result)texto += "\n" + c;
		return texto;
	}
	
	public String consultarClientePorNome(String nome) throws  Exception {
		Query q = manager.query();
		q.constrain(Cliente.class);
		q.descend("nome").constrain(nome);
		List<Cliente> result = q.execute(); 
		if (result.size() == 0)
			throw new Exception("\nConsultar cliente -  nao encontrado");
		return "O cliente �: "+result.get(0);
	}
	
	public  Cliente consultarClientePorNomeObj(String nome) throws  Exception {
		Query q = manager.query();
		q.constrain(Cliente.class);
		q.descend("nome").constrain(nome);
		List<Cliente> result = q.execute(); 
		if (result.size() == 0)
			throw new Exception("\nConsultar cliente -  nao encontrado");
		return result.get(0);
	}
	
	public Cliente consultarClientePorTelefone(String n) throws  Exception {
		Query q = manager.query();
		q.constrain(Cliente.class);
		q.descend("telefone").constrain(n); 
		List<Cliente> resultados = q.execute();
		if(resultados.size()==0)
			throw new Exception("\nConsultar cliente por telefone  - telefone nao encontrado");
		else
			return resultados.get(0);
	}
	
	public ArrayList<Animal> consultarAnimaisDoCliente(String nomeCliente) {
		Query q = manager.query();
		q.constrain(Cliente.class);
		q.descend("nome").constrain(nomeCliente);
		List<Cliente> resultados = q.execute();
		ArrayList<Animal> animais = new ArrayList<>();
		for(Animal ani : resultados.get(0).getAnimais()) {
			animais.add(ani);
		}
		return animais;
	}	
		
	public int consultarTotalClientes() {
		Query q = manager.query();
		q.constrain(Cliente.class);
		int total = q.execute().size(); 
		return total;
	}
	
	public List<Animal> consultaAnimalPorCliente(String cli) {
		Query q = manager.query();
		q.constrain(Animal.class);
		q.descend("c").constrain(cli); 
		List<Animal> result = q.execute(); 
		return result;
	}
	
	public int consultarUltimoIdCliente() {
		Query q = manager.query();
		q.constrain(Cliente.class);
		List<Cliente> resultados = q.execute();	
		if (resultados.size()>0) 
			return resultados.get(resultados.size()-1).getId();
		else
			return 0;
	}

}