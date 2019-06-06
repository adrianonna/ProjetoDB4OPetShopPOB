package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.Atendimento;

public class Cliente {
	
	private int id;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	
	private List<Animal> animais = new ArrayList<>();
	private List<Cliente> telefones = new ArrayList<>();
	

	public Cliente(int id, String nome, String endereco, String telefone, String email) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
	}
	
	public Cliente(int id, String nome, String telefone) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Animal> getAnimais() {
		return this.animais;
	}

	public void setAnimal(Animal animal) {
		this.animais.add(animal);
	}
	
	public List<Cliente> getTelefones() {
		return this.telefones;
	}

	public void setTelefone(Cliente telefone) {
		this.telefones.add(telefone);
	}
	
	public Cliente Filtro() {
		return null;
	}
	
	public Object localizarAnimal(String nomeAnimal) {
		for(Animal i : this.animais) {
			if (i.getNome() == nomeAnimal) {
				return i;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", email="
				+ email + " animais: " + this.getAnimais() + "]";
	}


	
	
	
}
