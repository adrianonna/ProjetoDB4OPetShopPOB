package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Atendimento {
	private int cod;
	private double precoTotal;
	private LocalDate data;
	private String funcionario;
	private Animal animal;
	
	private List<Produto> produtos = new ArrayList<Produto>();
	private List<Servico> servicos = new ArrayList<Servico>();
	
	public Atendimento(int cod, LocalDate data, String funcionario) {
		super();
		this.cod = cod;
		this.data = data;
		this.funcionario = funcionario;
	}
	
	public Atendimento(int cod, LocalDate data, String funcionario, Animal animal) {
		super();
		this.cod = cod;
		this.data = data;
		this.funcionario = funcionario;
		this.animal = animal;
	}


	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Produto produto) {
		produtos.add(produto);
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(Servico servico) {
		this.servicos.add(servico);
	}
	

	@Override
	public String toString() {
		return "Atendimento [cod=" + cod + ", PreçoTotal: "+ precoTotal +", data=" + data + ", funcionario="
				+ funcionario + ", animal=" + this.getAnimal() + ", produtos=" + this.getProdutos() +
				", servicos=" + this.getServicos() + "]";
	}

//	@Override
//	public String toString() {
//		return "Atendimento [cod=" + cod + ", precoTotal=" + precoTotal + ", data=" + data + ", funcionario="
//				+ funcionario + ", produtos=" + this.getProdutos() + ", servicos=" + this.getServicos() + "]";
//	}
	
	


	

	
	

}
