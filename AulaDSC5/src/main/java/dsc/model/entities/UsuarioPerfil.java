package dsc.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UsuarioPerfil {

		
	@Id
	@Column(length = 11, 
			nullable = false, 
			unique = true, 
			updatable = false)
	private String cpf;
	
	@Column(length = 10, 
			nullable = false)
	private String perfil;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
}
