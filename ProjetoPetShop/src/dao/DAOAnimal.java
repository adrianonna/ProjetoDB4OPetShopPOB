package dao;

import java.util.List;
import com.db4o.query.Query;
import modelo.Animal;
import modelo.Atendimento;
import modelo.Cliente;

public class DAOAnimal extends DAO<Animal>{
	//Leitura POR nome 
	public Animal read (Object chave) {
		String nome = (String) chave;
		Query q = manager.query();
		q.constrain(Animal.class);
		q.descend("nome").constrain(nome);
		List<Animal> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}




	//CONSULTAS
	
	public Animal consultarServicoAnimal(String nomeAnimal) {
		Query q = manager.query();
		q.constrain(Animal.class);
		q.descend("nome").constrain(nomeAnimal);
		List<Animal> animal = q.execute();
		return animal.get(0);
	}
	
	

	public int consultarUltimoIdAnimal() {
		Query q = manager.query();
		q.constrain(Animal.class);
		List<Animal> resultados = q.execute();	
		if (resultados.size()>0) 
			return resultados.get(resultados.size()-1).getCod();
		else 
			return 0;
	}

	public String consultarDonoERacaAnimal(String nomeAnimal) {
		Query q = manager.query();
		q.constrain(Animal.class);
		q.descend("nome").constrain(nomeAnimal);
		List<Animal> resultados = q.execute();
		if (resultados.size() > 0)
			return "cliente: " + resultados.get(0).getCliente().getNome() + " | possui animal raca: " + resultados.get(0).getRaca().getDescricao();
		return "não encontrado";
	}


}