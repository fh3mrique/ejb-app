package dsc.model.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

	private String rua;
	private Integer numero;
		
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}	
}
