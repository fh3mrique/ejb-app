package dsc.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import dsc.model.entities.Usuario;
import dsc.model.entities.UsuarioPerfil;
import dsc.model.repository.UsuarioRepositorio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.xml.bind.DatatypeConverter;

@Stateless
public class UsuarioBean {

	@EJB
	private UsuarioRepositorio urep;
	
	public Usuario cadastrarUsuario(Usuario u, UsuarioPerfil perfil) 
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		if (!this.validarUsuario(u)) {
			return null;
		}
		u.setSenha(encodeSHA256(u.getSenha()));
		this.urep.saveUsuarioPerfil(perfil);
		return this.urep.saveUsario(u);		
	}
	
	private boolean validarUsuario(Usuario u) {
		boolean ret = true;
		
		if (u == null ||
			u.getCpf() == null ||
			u.getCpf().isEmpty() ||
			u.getEndereco() == null){
			ret = false;
		}
		
		return ret;
	}

	public List<Usuario> buscarTodos() {
		return this.urep.findAll();
	}

	public Usuario buscarPorCPF(String cpfUsuario) {
		if (cpfUsuario == null || 
				cpfUsuario.isEmpty()) {
			return null;
		}
		
		return this.urep.findByCPF(cpfUsuario);
	}

	public Usuario atualizarUsuario(Usuario u) {
		if (!this.validarUsuario(u)) {
			return null;
		}
		
		return this.urep.atualizar(u);	
	}
	
	private static String encodeSHA256(String password) 
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printBase64Binary(digest).toString();
    }
}
