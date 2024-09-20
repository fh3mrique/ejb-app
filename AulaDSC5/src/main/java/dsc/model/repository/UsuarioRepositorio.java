package dsc.model.repository;

import java.util.List;

import dsc.model.entities.Usuario;
import dsc.model.entities.UsuarioPerfil;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Singleton
public class UsuarioRepositorio {

	@PersistenceContext
	(name = "auladsc")
    private EntityManager em;
	
	public Usuario saveUsario(Usuario u) {
		this.em.persist(u);
		this.em.flush();
		return u;
	}

	public List<Usuario> findAll() {
		Query q = em.createQuery("from Usuario u", Usuario.class);
        
		return q.getResultList();
	}

	public Usuario findByCPF(String cpfUsuario) {
		Query q = em.createQuery("from Usuario u where u.cpf = :cpf", Usuario.class);
		q.setParameter("cpf", cpfUsuario);
		return (Usuario) q.getSingleResult();
	}

	public Usuario atualizar(Usuario u) {		
		return this.em.merge(u);
	}

	public void saveUsuarioPerfil(UsuarioPerfil perfil) {
		this.em.persist(perfil);
	}
}
