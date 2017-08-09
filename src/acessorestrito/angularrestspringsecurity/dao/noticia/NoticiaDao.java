package acessorestrito.angularrestspringsecurity.dao.noticia;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import acessorestrito.angularrestspringsecurity.dao.Dao;
import acessorestrito.angularrestspringsecurity.entity.Noticia;

public interface NoticiaDao extends Dao<Noticia, Integer> {
	
}
