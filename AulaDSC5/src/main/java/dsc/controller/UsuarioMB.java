package dsc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dsc.model.UsuarioBean;
import dsc.model.entities.Conjuge;
import dsc.model.entities.Endereco;
import dsc.model.entities.Usuario;
import dsc.model.entities.UsuarioPerfil;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SessionScoped
@Named
public class UsuarioMB implements Serializable{
	
	private static final long serialVersionUID = -4272609180484517298L;
	
	private String cpf;
	private String nome;
	private Integer idade;
	
	private String rua;
	private String senha;
	private String perfil;
	private Integer numero;
	private List<Usuario> usuarios = new ArrayList<>();
	
	@EJB
	private UsuarioBean ubean;

	public String cadastrarUsuario() {
		Endereco end = new Endereco();
		end.setRua(this.rua);
		end.setNumero(this.numero);
		Usuario u = new Usuario(nome,idade,cpf, end);
		u.setSenha(this.senha);
		
		UsuarioPerfil perfil = new UsuarioPerfil();
		perfil.setCpf(u.getCpf());
		perfil.setPerfil(this.perfil);
		
		try {
			Usuario uRet = this.ubean.cadastrarUsuario(u, perfil);
			if (uRet == null) {
				
			}
			this.usuarios = this.ubean.buscarTodos();
			this.cpf = "";
			this.idade = 0;
			this.nome = "";
			return "login.xhtml";
		}catch (Exception ex) {
			return "error.xhtml";
		}		
	}
	
	public void cadastrarConjuge(String cpfUsuario) {
		Conjuge conga = new Conjuge(nome,idade,cpf);
		try {
			Usuario uRet = this.ubean.buscarPorCPF(cpfUsuario);
			uRet.setConga(conga);
			
			uRet = this.ubean.atualizarUsuario(uRet);
			if (uRet == null) {
				
			}
			this.usuarios = this.ubean.buscarTodos();
			this.cpf = "";
			this.idade = 0;
			this.nome = "";
		}catch (Exception ex) {
			
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}	
	
}
