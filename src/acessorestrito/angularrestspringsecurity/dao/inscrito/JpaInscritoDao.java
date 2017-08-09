package acessorestrito.angularrestspringsecurity.dao.inscrito;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import acessorestrito.angularrestspringsecurity.dao.JpaDao;

import acessorestrito.angularrestspringsecurity.entity.Inscrito;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaInscritoDao extends JpaDao<Inscrito, Integer> implements InscritoDao {
	public JpaInscritoDao() {
		super(Inscrito.class);
	}

}