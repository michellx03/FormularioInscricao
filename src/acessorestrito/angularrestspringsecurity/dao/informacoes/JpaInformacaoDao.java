package acessorestrito.angularrestspringsecurity.dao.informacoes;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import acessorestrito.angularrestspringsecurity.dao.JpaDao;

import acessorestrito.angularrestspringsecurity.entity.Informacoes;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaInformacaoDao extends JpaDao<Informacoes, Integer> implements InformacaoDao {
	public JpaInformacaoDao() {
		super(Informacoes.class);
	}

}