package dao;

import java.util.List;
import com.db4o.query.Query;

import modelo.Cliente;
import modelo.Servico;

public class DAOServico extends DAO<Servico>{
	//Leitura POR nome 
	public Servico read (Object chave) {
		String nome = (String) chave;
		Query q = manager.query();
		q.constrain(Servico.class);
		q.descend("nome").constrain(nome);
		List<Servico> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	// por parte do nome
	public  List<Servico> consultarServicoPorParteNome(String nome) {
		Query q = manager.query();
		q.constrain(Servico.class);
		q.descend("nome").constrain(nome).like();
		List<Servico> result = q.execute(); 
		return result;
	}
}

