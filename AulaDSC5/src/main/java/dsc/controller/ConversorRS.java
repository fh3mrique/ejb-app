package dsc.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/conversor")
public class ConversorRS {
	
	@GET
    public String pint() {
        return "Valendo!";
    }
	
}
