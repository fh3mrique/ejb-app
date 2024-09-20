package dsc.controller;

import java.security.Principal;
import java.util.Map;

import dsc.model.UsuarioBean;
import dsc.model.entities.Usuario;
import jakarta.annotation.security.DeclareRoles;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@ApplicationScoped
@DeclareRoles({ "user", "admin" })
@Named
public class LoginMB {

	@EJB
	private UsuarioBean userBean;
	
	private String cpf;
	private String senha;
	
	private Usuario user;

	public String login() {

		FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
			request.login(cpf, senha);
		} catch (ServletException e) {
			return "error.xhtml";
		}
        
        Principal principal = request.getUserPrincipal();
        String name = principal.getName();
        this.user = userBean.buscarPorCPF(cpf);
        
        ExternalContext externalContext = 
        		FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = 
        		externalContext.getSessionMap();
        sessionMap.put("usuarioLogado", this.user);
        if (request.isUserInRole("user")) {
            return "/user/home.xhtml?faces-redirect=true";
        } else if (request.isUserInRole("admin")){
            return "/admin/home.xhtml?faces-redirect=true";
        } else {
        	return "error.xhtml";
        }
		
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUser() {
		return user;
	}
}
