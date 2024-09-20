package dsc.model.entities;

import jakarta.ejb.Stateless;

@Stateless
public class Conversor {

	
	public double converterCelsiusFahrenheit(double graus) {
		
		if (graus < -273.15) {
			throw new IllegalArgumentException();
		}
		
		return (1.8*graus) + 32;
	}	
	
}
