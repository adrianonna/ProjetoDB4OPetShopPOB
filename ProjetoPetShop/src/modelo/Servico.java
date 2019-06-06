package modelo;

public class Servico {

	private String nome;
	private double preco;
	private String obs;
	
	public Servico(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	@Override
	public String toString() {
		return "Servico [nome=" + nome + ", preco=" + preco + ", obs=" + obs + "]";
	}
	
	
	
	
}
