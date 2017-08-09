package acessorestrito.angularrestspringsecurity.dao.inscrito;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import acessorestrito.angularrestspringsecurity.dao.Dao;
import acessorestrito.angularrestspringsecurity.entity.Inscrito;

public interface InscritoDao extends Dao<Inscrito, Integer> {
	
}
