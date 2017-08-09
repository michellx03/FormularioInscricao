package acessorestrito.angularrestspringsecurity.dao.status;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import acessorestrito.angularrestspringsecurity.dao.Dao;
import acessorestrito.angularrestspringsecurity.entity.StatusInscrito;

public interface StatusDao extends Dao<StatusInscrito, Integer> {
	
}