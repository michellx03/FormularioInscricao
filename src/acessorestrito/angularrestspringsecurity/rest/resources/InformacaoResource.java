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
//import Uteis.DadosUsuarioEmail;
//import Uteis.EnviarEmail;
import acessorestrito.angularrestspringsecurity.JsonViews;
import acessorestrito.angularrestspringsecurity.dao.JpaDao;
import acessorestrito.angularrestspringsecurity.dao.informacoes.InformacaoDao;
import acessorestrito.angularrestspringsecurity.entity.AccessToken;
import acessorestrito.angularrestspringsecurity.entity.Informacoes;
import acessorestrito.angularrestspringsecurity.entity.Role;
import acessorestrito.angularrestspringsecurity.entity.Usuario;
import javassist.expr.NewArray;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
//import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;

@Component
@Path("/informacao")
public class InformacaoResource {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Autowired
	private InformacaoDao informacaoDaoInterface;

	private InformacaoDao informacaoDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public  List<Informacoes> listarInformacoes() {
		
		List<Informacoes> listarInformacao = this.informacaoDaoInterface.findAll();
		
		return listarInformacao;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/alteracao")
	public Informacoes Alterar(@QueryParam("id") Integer id) {

	return this.informacaoDaoInterface.find(id);
	
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/alterar")
	public Informacoes Alteracao(Informacoes informacoes) {
	
	return this.informacaoDaoInterface.save(informacoes);
	}
}
