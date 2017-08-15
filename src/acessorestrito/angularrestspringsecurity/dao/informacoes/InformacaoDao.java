package acessorestrito.angularrestspringsecurity.dao.informacoes;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import acessorestrito.angularrestspringsecurity.dao.Dao;
import acessorestrito.angularrestspringsecurity.entity.Informacoes;

public interface InformacaoDao extends Dao<Informacoes, Integer> {
	
}