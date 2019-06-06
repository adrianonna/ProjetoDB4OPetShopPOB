package dao;

import java.util.List;
import com.db4o.query.Query;

import modelo.Cliente;
import modelo.Produto;
import modelo.Servico;

public class DAOProduto extends DAO<Produto>{
	//Leitura POR nome 
	public Produto read (Object chave) {
		String nome = (String) chave;
		Query q = manager.query();
		q.constrain(Produto.class);
		q.descend("nome").constrain(nome);
		List<Produto> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	// por parte do nome
		public List<Produto> consultarProdutoPorParteNome(String nome) {
			Query q = manager.query();
			q.constrain(Produto.class);
			q.descend("nome").constrain(nome).like();
			List<Produto> result = q.execute(); 
			return result;
		}

		public int consultarUltimoIdProduto() {
			Query q = manager.query();
			q.constrain(Produto.class);
			List<Produto> resultados = q.execute();	
			if (resultados.size()>0) 
				return resultados.get(resultados.size()-1).getId();
			else
				return 0;
		}
}

