package Fachada;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import dao.DAO;
import dao.DAOAnimal;
import dao.DAOAtendimento;
import dao.DAOCliente;
import dao.DAOProduto;
import dao.DAORaca;
import dao.DAOServico;
import modelo.Animal;
import modelo.Cliente;
import modelo.Produto;
import modelo.Raca;
import modelo.Servico;
import modelo.Atendimento;


public class Fachada {
	private static DAOCliente daocliente = new DAOCliente();
	private static DAOAnimal daoanimal = new DAOAnimal();
	private static DAOProduto daoproduto = new DAOProduto();
	private static DAOAtendimento daoatendimento = new DAOAtendimento();
	private static DAORaca daoraca = new DAORaca();
	private static DAOServico daoservico = new DAOServico();
	private static double totalProd;
	private static double totalServ;

	public static void inicializar(){
		DAO.open();
	}
	
	
//	CADASTROS
	
	public static Cliente cadastrarCliente(String nome, String telefone) throws  Exception{
		DAO.begin();	
		Cliente c = daocliente.read(nome.toLowerCase());
		if(c != null)
			throw new Exception("cadastrar pessoa - pessoa ja cadastrado:" + nome);

		int idcliente = daocliente.consultarUltimoIdCliente();
		c = new Cliente(idcliente+1, nome.toLowerCase(), telefone);
		daocliente.create(c);	
		DAO.commit();
		System.out.println("Cliente cadastrado!");
		return c;
	}
	
	public static Cliente cadastrarCliente(String nome, String endereco, String telefone, String email) throws  Exception{
		DAO.begin();	
		Cliente c = daocliente.read(nome.toLowerCase());
		if(c != null)
			throw new Exception("cadastrar pessoa - pessoa ja cadastrado:" + nome);

		int idcliente = daocliente.consultarUltimoIdCliente();
		c = new Cliente(idcliente+1, nome.toLowerCase(), endereco.toLowerCase(), telefone, email.toLowerCase());
		daocliente.create(c);	
		DAO.commit();
		System.out.println("Cliente cadastrado!");
		return c;
	}
	
	public static Produto cadastrarProduto(String nome, double preco) throws  Exception{
		DAO.begin();	
		Produto p = daoproduto.read(nome.toLowerCase());
		if(p != null)
			throw new Exception("cadastrar produto - produto ja cadastrado:" + nome);

		
		int idproduto = daoproduto.consultarUltimoIdProduto();
		p = new Produto(idproduto+1, nome.toLowerCase(), preco);
		daoproduto.create(p);	
		DAO.commit();
		System.out.println("Produto Cadastrado!");
		return p;
	}
	
	public static Atendimento cadastrarAtendimento(LocalDate data, String funcionario, String animal) throws  Exception{
		DAO.begin();
		Animal a = daoanimal.read(animal.toLowerCase());
		if(a == null)
			throw new Exception("Animal não cadastrado, cadastrar antes!");

		int ultimoAtend = daoatendimento.consultarUltimoAtendimento();
		Atendimento aten = new Atendimento(ultimoAtend+1, data, funcionario, a);		
		daoatendimento.create(aten);
		daoatendimento.update(aten);
		a.setAtendimentos(aten);
		daoanimal.update(a);
		DAO.commit();
		System.out.println("Atendimento Cadastrado!");
		return aten;
	}
	
	public static Animal cadastrarAnimal(String nome, Date data_nasc, String sexo, Double peso, Double comprimento, String raca) throws  Exception{
		DAO.begin();
		
		Animal a = daoanimal.read(nome.toLowerCase());
		if(a != null)
			throw new Exception("cadastrar animal - animal ja cadastrado: " + nome);
		Raca r = daoraca.read(raca.toLowerCase());
		if(r == null)
			throw new Exception("Raca nao cadastrada: " + raca);
		
		int idAnimal = daoanimal.consultarUltimoIdAnimal();
		a = new Animal(idAnimal+1, nome.toLowerCase(), data_nasc, sexo, peso, comprimento, r);
		daoanimal.create(a);	
		DAO.commit();
		System.out.println("Animal Cadastrado!");
		return a;
	}
	
	public static Animal cadastrarAnimal(String nome, String raca) throws  Exception{
		DAO.begin();
		Animal a = daoanimal.read(nome.toLowerCase());
		if(a != null)
			throw new Exception("cadastrar animal - animal ja cadastrado: " + nome);
		Raca r = daoraca.read(raca.toLowerCase());
		if(r == null)
			throw new Exception("Raca nao cadastrada: " + raca);
		
		int idAnimal = daoanimal.consultarUltimoIdAnimal();
		a = new Animal(idAnimal+1, nome.toLowerCase(), r);
		daoanimal.create(a);	
		DAO.commit();
		System.out.println("Animal Cadastrado!");
		return a;
	}
	
	public static Raca cadastrarRaca(String nome, String tamanho, String especie) throws  Exception{
		DAO.begin();
		Raca r = daoraca.read(nome.toLowerCase());
		if(r != null)
			throw new Exception("cadastrar raca - raca ja cadastrado: " + nome);
		
		r = new Raca(nome.toLowerCase(), tamanho.toLowerCase(), especie.toLowerCase());
		
		daoraca.create(r);	
		DAO.commit();
		System.out.println("Raca Cadastrado!");
		return r;
	}
	
	public static Servico cadastrarServico(String nome, double preco) throws  Exception{
		DAO.begin();
		Servico s = daoservico.read(nome.toLowerCase());
		if(s != null)
			throw new Exception("cadastrar Servico - Servico ja cadastrado: " + nome);
		
		s = new Servico(nome.toLowerCase(), preco);
		daoservico.create(s);
		DAO.commit();
		System.out.println("Servico Cadastrado!");
		return s;
	}
	
	public static void adicionarAnimalDoCliente(String nomeAnimal, String nomeCliente) throws  Exception {
		DAO.begin();
		
		Animal ani = daoanimal.read(nomeAnimal.toLowerCase());
		if(ani == null)
			throw new Exception("Animal nao cadastrado: " + nomeAnimal+". Cadastrar antes!");

		Cliente cli = daocliente.read(nomeCliente.toLowerCase());
		if (cli == null) {
			throw new Exception("Cliente nao cadastrado: "+ nomeCliente);
		}
		else {
			if (cli.localizarAnimal(ani.getNome()) != null) {
				throw new Exception("Animal "+nomeAnimal+" já cadastrado nesse cliente "+nomeCliente+".");
			}
		}
		cli.setAnimal(ani);
		ani.setCliente(cli);
		daocliente.update(cli);
		daoanimal.update(ani);
		DAO.commit();
		System.out.println("Animal Adicionado ao Cliente!");
	}
	
	public static void adicionarServicoAtendimento(int idAtendimento, String servico) throws Exception {
		DAO.begin();
		
		Atendimento aten = daoatendimento.read(idAtendimento);
		if (aten == null) {
			throw new Exception("O atendimento não foi aberto. ");
		}
		Servico serv = daoservico.read(servico.toLowerCase());
		if (serv == null) {
			throw new Exception("Servico nao cadastrado. ");
		}
		aten.setServicos(serv);
		if (aten.getPrecoTotal() > 0) {
			aten.setPrecoTotal(aten.getPrecoTotal() + serv.getPreco());
		}else {
			aten.setPrecoTotal(serv.getPreco());
		}
		daoatendimento.update(aten);
		DAO.commit();
		System.out.println("Servico adicionado ao Atendimento!");
	}
	
	public static void adicionarProdutoAtendimento(int idAtendimento, String nomeProduto) throws Exception {
		DAO.begin();
		
		Atendimento aten = daoatendimento.read(idAtendimento);
		if (aten == null) {
			throw new Exception("O atendimento nao foi aberto. ");
		}
		Produto prod = daoproduto.read(nomeProduto.toLowerCase());
		if (prod == null) {
			throw new Exception("Produto nao cadastrado!");
		}
		aten.setProdutos(prod);
		if (aten.getPrecoTotal() > 0) {
			aten.setPrecoTotal(aten.getPrecoTotal() + prod.getPreco());
		}else {
			aten.setPrecoTotal(prod.getPreco());
		}
		daoatendimento.update(aten);
		//daoproduto.update(prod); precisa disso?
		DAO.commit();
		System.out.println("Produto adicionado ao Atendimento!");
	}
		
	
	
	
//	LISTAGEM
	
	public static String listarClientes(){
		List<Cliente> clientes = daocliente.readAll();
		String texto="-----------listagem de Clientes-----------\n";
		for (Cliente cli : clientes) {
			texto += cli +"\n";
		}
		return texto;
	}
	
	public static String listarProdutos(){
		List<Produto> produtos = daoproduto.readAll();
		String texto="-----------listagem de Produtos-----------\n";
		for (Produto p : produtos) {
			texto += p +"\n";
		}
		return texto;
	}
	
	public static String listarAnimais(){
		List<Animal> animais = daoanimal.readAll();
		String texto="-----------listagem de Animais-----------\n";
		for (Animal a : animais) {
			texto += a +"\n";
		}
		return texto;
	}
	
	public static String listarServicos(){
		List<Servico> servicos = daoservico.readAll();
		String texto="-----------listagem de Servicos-----------\n";
		for (Servico s : servicos) {
			texto += s +"\n";
		}
		return texto;
	}
	
	public static String listarRaca(){
		List<Raca> racas = daoraca.readAll();
		String texto="-----------listagem de Racas-----------\n";
		for (Raca r : racas) {
			texto += r +"\n";
		}
		return texto;
	}
	
	public static List<Raca> todasRacas(){		
		return daoraca.readAll();
	}
	
	public static List<Produto> todosProdutos(){
		return daoproduto.readAll();
	}
	
	public static List<Servico> todosServicos(){
		return daoservico.readAll();
	}
	
	public static String listarAtendimento(){
		List<Atendimento> atendimentos = daoatendimento.readAll();
		String texto="-----------listagem de Atendimentos-----------\n";
		for (Atendimento a : atendimentos) {
			texto += a +"\n";
		}
		return texto;
	}
	
	
	
//	BUSCA
	
	// buscas com 3 classes 
	public static String consultarClientesQueTenhamCompradoProdutoEServico(String nomeProduto, String nomeServico) throws  Exception {
		Servico serv = daoservico.read(nomeServico.toLowerCase());
		Produto prod = daoproduto.read(nomeProduto.toLowerCase());
		if (serv == null && prod == null) {
			throw new Exception("Servico ou produto nao cadastrado!");
		}
		String texto = daoatendimento.consultarClientesQueTenhamCompradoProdutoEServico(nomeProduto.toLowerCase(), nomeServico.toLowerCase());
		if(texto.isEmpty()) {
			throw new Exception("N�o existe cliente que consumiu o servico e produto em conjunto!");
		}
		return "Clientes que compraram "+ nomeProduto + " e " + nomeServico + " => "+texto;
	}
	
	public static String consultarClientesPorServicoOuProduto(String nome) throws  Exception {
		Servico serv = daoservico.read(nome.toLowerCase());
		Produto prod = daoproduto.read(nome.toLowerCase());
		if (serv == null && prod == null) {
			throw new Exception("Servico ou produto nao cadastrado!");
		}
		ArrayList<Cliente> clientes = daoatendimento.consultarClientesPorServicoOuProduto(nome);
		String nomeClientes = "";
		for (Cliente cliente : clientes) {
			nomeClientes += cliente.getNome()+", ";
		}
		if (prod != null)
			return "\nOs clientes que compraram o produto "+prod.getNome()+" sao: "+nomeClientes;
		else if (serv != null)
			return "\nOs clientes que compraram o servico "+serv.getNome()+" sao: "+nomeClientes;
		return null;
	}
	
	public static String consultarRacaConsumiuProduto(String nomeProduto, String nomeServico) throws Exception {
		Servico serv = daoservico.read(nomeServico.toLowerCase());
		Produto prod = daoproduto.read(nomeProduto.toLowerCase());
		if (serv == null && prod == null) {
			throw new Exception("Servico ou produto nao cadastrado!");
		}
		String result = daoatendimento.consultarRacaConsumiuProduto(nomeProduto, nomeServico);
		return "As racas que consumiram " + nomeProduto + " e " + nomeServico + " sao " + result;
	}
	
	// outras buscas
	
	public static String consultarDonoERacaAnimal(String nomeAnimal) {
		String resultado  = daoanimal.consultarDonoERacaAnimal(nomeAnimal);
		
		return resultado;
	}
	public static String consultarClientePorParteNome(String caracteres) {
		String result = daocliente.consultarClientePorParteNome(caracteres.toLowerCase());
		return result;
	}
	
//	public static String consultarClientePorNome(String caracteres)  throws  Exception {
//		String result = daocliente.consultarClientePorNome(caracteres.toLowerCase());
//		return "O cliente do nome "+caracteres+" � "+result;
//	}
	
	public static Cliente consultarClientePorNomeObj(String caracteres)  throws  Exception {
		Cliente result = daocliente.consultarClientePorNomeObj(caracteres.toLowerCase());
		return result;
	}
	
	
	public static String consultarClientePorTelefone(String tel) throws  Exception {
		Cliente result = daocliente.consultarClientePorTelefone(tel);
		return "O cliente do telefone "+tel+ " � " +result.getNome();
	}
	
	public static String consultarAnimaisDoCliente(String cli) throws  Exception {
		ArrayList<Animal> animais = daocliente.consultarAnimaisDoCliente(cli.toLowerCase());
		String texto = "";
		for(Animal ani : animais) {
			texto += ani.getNome()+", ";
		}		
		return "Os animais do cliente "+cli+" sao: "+texto;
	}
	
	public static String consultarClienteDoAnimal(String ani) throws  Exception {
		Animal a = daoanimal.read(ani.toLowerCase());
		if(a == null) 
			throw new Exception("\nAnimal "+ani+" nao encontrado");
		else
			return "Cliente dono de " + ani + " -> " + a.getCliente().getNome();
	}
	
	public static String consultarValorAtendimento(int id) throws  Exception {
		DAO.begin();
		Atendimento aten = daoatendimento.read(id);
		if(aten == null) {
			throw new Exception("\nAtendimento nao encontrado!");
		}
		double atendimento = daoatendimento.consultarValorAtendimento(id);
		return "O valor do atendimento "+id+" é "+atendimento;
	}
	
	public static String consultarClienteMaisConsumiu() {
		Atendimento atendimento = daoatendimento.consultarClienteMaisConsumiu();
		return "O cliente "+atendimento.getAnimal().getCliente().getNome()+" consumiu "+atendimento.getPrecoTotal();	
	}
	
//	public static String consultarClientesPorProduto(String nome) throws  Exception {
//		Produto prod = daoproduto.read(nome);
//		if (prod == null) {
//			throw new Exception("\nProduto nao cadastrado!");
//		}
//		ArrayList<Cliente> clientes = daoatendimento.consultarClientesPorProduto(nome);
//		
//		return "\nO clientes que compraram o produto \""+nome+"\" sao: "+clientes;
//	}
	
	public static String consultarClientesPorProduto(String nome) throws  Exception {
		Produto prod = daoproduto.read(nome);
		if (prod == null) {
			throw new Exception("\nProduto n�o cadastrado!");
		}
		ArrayList<Cliente> clientes = daoatendimento.consultarClientesPorProduto(nome);
		if (clientes.isEmpty()) {
			throw new Exception("\nNenhum cliente comprou esse produto!");
		}
		String nomeClientes = "";
		for(Cliente cli : clientes) {
			nomeClientes += cli.getNome()+", ";
		}
		return "\nO clientes que compraram o produto \""+nome+"\" s�o: "+nomeClientes;
	}
	
	public static String consultarClientesPorServico(String nome) throws  Exception {
		Servico serv = daoservico.read(nome);
		if (serv == null) {
			throw new Exception("\nServi�o n�o cadastrado!");
		}
		ArrayList<Cliente> clientes = daoatendimento.consultarClientesPorServico(nome);
		if (clientes.isEmpty()) {
			throw new Exception("\nNenhum cliente comprou esse servi�o!");
		}
		String nomeClientes = "";
		for(Cliente cli : clientes) {
			nomeClientes += cli.getNome()+", ";
		}
		return "\nO clientes que compraram o servi�o \""+nome+"\" s�o: "+nomeClientes;
	}
		
	
	public static String consultarClientesPorServicoEProduto(String nomeServ, String nomeProd) throws  Exception {
		Servico serv = daoservico.read(nomeServ.toLowerCase());
		Produto prod = daoproduto.read(nomeProd.toLowerCase());
		if (serv == null && prod == null) {
			throw new Exception("Servico ou produto nao cadastrado!");
		}
		List<Atendimento> atendimentos = daoatendimento.consultarClientesPorServicoEProduto(serv.getNome(), prod.getNome());
		if (atendimentos.isEmpty()) {
			throw new Exception("N�o existe atendimento com com esse produto e servi�o!");
		}
		String texto = "\nClientes que compraram o produto \""+nomeProd+"\" e servi�o \""+nomeServ+"\" s�o: ";
		for (Atendimento aten : atendimentos) {
			texto += aten.getAnimal().getCliente().getNome()+", animal: "+aten.getAnimal().getNome()+" | ";
		}
		return texto;
	}
	
	public static String consultarServicoAnimal(String nomeAnimal) throws Exception {
		Animal ani = daoanimal.read(nomeAnimal.toLowerCase());
		if (ani == null) {
			throw new Exception("Animal nao cadastrado!");
		}
		Animal a = daoanimal.consultarServicoAnimal(nomeAnimal.toLowerCase());
		String nomeServicos="";
		for (Atendimento aten : a.getAtendimentos()) {
			nomeServicos += aten.getServicos()+", ";
		}
		
		return "Os servi�os do animal: "+nomeAnimal+" s�o: "+nomeServicos;
	}
	
	public static String consultarAtendimentoQuantidadeProdutos(int qtd) throws  Exception {
		DAO.begin();
		List<Atendimento> atendimentos = daoatendimento.consultarAtendimentoQuantidadeProdutos(qtd);
		if (atendimentos == null)
			throw new Exception("\n Consultar quantidade de atendimentos - nao encontrado!");
		String result = "Cliente com atendimento com mais de " + qtd + " produtos: \n";
		for (Atendimento atende : atendimentos) {
			result += atende.getAnimal().getCliente().getNome() + " - produtos: " + atende.getProdutos() + "\n";
		}
		DAO.commit();
		return result;
	}
	
	
	
	// DELETAR
	
	public static String excluirCliente(String nomeCliente) throws Exception {
		DAO.begin();
		Cliente cli = daocliente.read(nomeCliente.toLowerCase());
		if (cli == null)
			throw new Exception("excluir cliente - nome n�o existente");
		
		daocliente.delete(cli);
		DAO.commit();
		return "Cliente excluido com sucesso";		
	}
	
	public static String excluirProduto(String nomeProduto) throws Exception {
		DAO.begin();
		Produto prod = daoproduto.read(nomeProduto.toLowerCase());
		if (prod == null)
			throw new Exception("excluir produto - nome nao existente");
		
		daoproduto.delete(prod);
		DAO.commit();
		return "Produto excluido com sucesso";		
	}
	
	public static String excluirServico(String nomeServico) throws Exception {
		DAO.begin();
		Servico serv = daoservico.read(nomeServico.toLowerCase());
		if (serv == null)
			throw new Exception("excluir produto - servico nao existente");
		
		daoservico.delete(serv);
		DAO.commit();
		return "Serviço excluido com sucesso";
	}
	
	
	
	// ATUALIZAR
	
	public static void atualizarAtendimentos() {
		List<Atendimento> atendimentos = daoatendimento.readAll();
		for(Atendimento atendi : atendimentos) {
			
		}
	}
	
	
	public static String alterarEnderecoCliente(String nomeCliente, String endereco) throws Exception {
		DAO.begin();
		Cliente cli = daocliente.read(nomeCliente.toLowerCase());
		
		if (cli == null)
			throw new Exception("alterar endereco cliente - Cliente não existente");
		cli.setEndereco(endereco.toLowerCase());
		cli = daocliente.update(cli);
		DAO.commit();
		return "Endereco alterado com sucesso";
	}
	
	public static String atualizarCliente(String nomeCliente, String telefone, String email, String endereco) throws Exception {
		DAO.begin();
		Cliente cli = daocliente.read(nomeCliente.toLowerCase());
		
		if (cli == null)
			throw new Exception("alterar cliente - Cliente nao existente");
		cli.setNome(nomeCliente.toLowerCase());
		cli.setTelefone(telefone);
		cli.setEmail(email.toLowerCase());
		cli.setEndereco(endereco.toLowerCase());
		cli = daocliente.update(cli);
		DAO.commit();
		return "cliente alterado com sucesso";
	}
	
	public static String atualizarProduto(String nomeProduto,double preco) throws Exception {
		DAO.begin();
		Produto prod = daoproduto.read(nomeProduto.toLowerCase());
		
		if (prod == null)
			throw new Exception("alterar produto - produto nao existente");
		prod.setNome(nomeProduto.toLowerCase());;
		prod.setPreco(preco);
		prod = daoproduto.update(prod);
		DAO.commit();
		return "produto alterado com sucesso";
	}
	
	public static String atualizarServico(String nomeServico,double preco) throws Exception {
		DAO.begin();
		Servico serv = daoservico.read(nomeServico.toLowerCase());
		
		if (serv == null)
			throw new Exception("alterar servico - servico nao existente");
		serv.setNome(nomeServico.toLowerCase());;
		serv.setPreco(preco);
		serv = daoservico.update(serv);
		DAO.commit();
		return "servico alterado com sucesso";
	}
	
	public static String alterarPrecoServico(String nomeServico, double preco) throws Exception {
		DAO.begin();
		Servico servico = daoservico.read(nomeServico.toLowerCase());
		
		if (servico == null)
			throw new Exception("alterar preco servico - Servico nao existente");
		servico.setPreco(preco);
		servico = daoservico.update(servico);
		DAO.commit();
		return "Preco do servico alterado";
	}
	

	// FINALIZAR
	public static void finalizar(){
		DAO.close();
	}

}