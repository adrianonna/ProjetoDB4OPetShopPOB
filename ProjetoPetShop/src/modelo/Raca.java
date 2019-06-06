package modelo;

public class Raca {
	private String nome;
	private String tamanho;
	private String especie;
	
	public Raca(String nome, String tamanho, String especie) {
		super();
		this.nome = nome;
		this.tamanho = tamanho;
		this.especie = especie; //tipo do animal, gato, cachorro
	}
	
	public Raca(String nome, String especie) {
		super();
		this.nome = nome;
		this.especie = especie; //tipo do animal, gato, cachorro
	}
	
	public String getDescricao() {
		return nome;
	}
	public void setDescricao(String nome) {
		this.nome = nome;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	@Override
	public String toString() {
		return "Raca [descricao=" + nome + ", tamanho=" + tamanho + ", especie=" + especie + "]";
	}
	
	
	
	
	
	
}
