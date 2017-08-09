package acessorestrito.angularrestspringsecurity.rest.resources;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
//import Uteis.Conversores;
import acessorestrito.angularrestspringsecurity.JsonViews;
import acessorestrito.angularrestspringsecurity.dao.JpaDao;
import acessorestrito.angularrestspringsecurity.dao.ano.AnoDao;
import acessorestrito.angularrestspringsecurity.entity.AccessToken;
import acessorestrito.angularrestspringsecurity.entity.Ano;
import javassist.expr.NewArray;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
@Path("/ano")
public class AnoResource {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Autowired
	private AnoDao anoDaoInterface;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/obterAnos")
	public List<Ano> list() {

		// Native Query
		/*
		 * 
		 * List<Ano> customers = (List<Ano>)entityManager.createNativeQuery
		 * ("SELECT * FROM sistema.ano where ano_id = 3 OR ano_id=4 OR ano_id = 2"
		 * , Ano.class).getResultList(); Iterator i = customers.iterator();
		 * List<Ano> anobusca = new ArrayList<>(); Ano year; while (i.hasNext())
		 * { year = (Ano)i.next(); anobusca.add(year); }
		 */

		// JPQL

		String query = "SELECT a from Ano a WHERE a.anoId=2";
		List<Ano> anos = entityManager.createQuery(query, Ano.class).getResultList();
		entityManager.close();
		List<Ano> anobusca = new ArrayList<>();
		for (Ano ano : anos) {
			anobusca.add(ano);
		}
		return anobusca;
	}

}
