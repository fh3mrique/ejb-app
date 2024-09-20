package dsc.model.entities;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Conjuge{
	
	@Id
	@GeneratedValue(
	strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false, 
			length = 50)	
	@NotBlank
	private String nome;	
	@Column(updatable = true)
	private int idade;
	@Column(length = 11, 
			nullable = false, 
			unique = true, 
			updatable = false)
	private String cpf;
		
	public Conjuge() {
		
	}

	public Conjuge(String nome, int idade, String cpf) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}	

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conjuge other = (Conjuge) obj;
		return Objects.equals(cpf, other.cpf) &&
				Objects.equals(id, other.id);
	}
}
