package acessorestrito.angularrestspringsecurity.dao.noticia;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import acessorestrito.angularrestspringsecurity.dao.JpaDao;

import acessorestrito.angularrestspringsecurity.entity.Noticia;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaNoticiaDao extends JpaDao<Noticia, Integer> implements NoticiaDao {
	public JpaNoticiaDao() {
		super(Noticia.class);
	}

}
