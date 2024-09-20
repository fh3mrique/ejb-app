package dsc.controller;

import dsc.model.entities.Conversor;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class ConversorMB {

	@EJB
	private Conversor conversor;
	
	private double graus;
	private double result;
	
	public void converteCelFah() {
		this.result = this.conversor.
				converterCelsiusFahrenheit(this.graus);
	}

	public double getGraus() {
		return graus;
	}

	public void setGraus(double graus) {
		this.graus = graus;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}	
}
