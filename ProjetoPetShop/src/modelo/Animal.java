package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Raca;

public class Animal {
	private int cod;
	private String nome;
	private Date data_nasc;
	private String sexo;
	private Double peso;
	private Double comprimento;
	private Raca raca;
	private Cliente c;
	
	private List<Atendimento> atendimentos = new ArrayList<>();
	
	
	public Animal(int cod, String nome, Date data_nasc, String sexo, Double peso, Double comprimento, Raca raca) {
		this.cod = cod;
		this.nome = nome;
		this.data_nasc = data_nasc;
		this.sexo = sexo;
		this.peso = peso;
		this.comprimento = comprimento;
		this.raca = raca;
	}
	
	public Animal(int cod, String nome, Raca raca) {
		this.cod = cod;
		this.nome = nome;
		this.raca = raca;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getComprimento() {
		return comprimento;
	}

	public void setComprimento(Double comprimento) {
		this.comprimento = comprimento;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}
	
	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Atendimento atendimento) {
		this.atendimentos.add(atendimento);
	}

	public Cliente getCliente() {
		return c;
	}

	public void setCliente(Cliente c) {
		this.c = c;
	}


	@Override
	public String toString() {
		return "Animal [cod=" + cod + ", nome=" + nome + ", data_nasc=" + data_nasc + ", sexo=" + sexo + 
				", peso="+ peso + ", comprimento=" + comprimento + ", raca=" + raca.getDescricao() +
				"]";
	}
	
	
	
}
