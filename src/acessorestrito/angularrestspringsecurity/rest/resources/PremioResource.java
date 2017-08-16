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
import acessorestrito.angularrestspringsecurity.dao.premio.PremioDao;
import acessorestrito.angularrestspringsecurity.entity.AccessToken;
import acessorestrito.angularrestspringsecurity.entity.Premio;
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
@Path("/premio")
public class PremioResource {
	
	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Autowired
	private PremioDao premioDaoInterface;

	private PremioDao premioDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public  List<Premio> listarPremio() {
		
		List<Premio> listarPremio = this.premioDaoInterface.findAll();
		
		return listarPremio;
	}

	
	@Path("Cadastrar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Premio Cadastrar(Premio premio) {
		
		return this.premioDaoInterface.save(premio);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/alteracao")
	public Premio Alterar(@QueryParam("id") Integer id) {

	return this.premioDaoInterface.find(id);
}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/alterar")
	public Premio Alteracao(Premio premio) {
	
	return this.premioDaoInterface.save(premio);
}


	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public void delete(@QueryParam("id") Integer id) {
		this.premioDaoInterface.delete(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/obterPremios")
	public obterPremios obterDados(@QueryParam("id") Integer id) {
		
		String select = "SELECT prem_id, prem_premio, prem_status, stpr_id, stpr_status "
						+"FROM sistema.premio "
						+"INNER JOIN sistema.status_premio ON prem_status = stpr_id "
						+"WHERE prem_id ="+id+"";
		
		Query query = entityManager.createNativeQuery(select);
		List<Object[]> list = query.getResultList();
		
		obterPremios retorna = new obterPremios();
		
		for (Object[] obj : list) {
			//retornUsuario.setDausNomeCompleto(obj[0]==null?"":obj[0].toString())
			retorna.setPremId(Long.parseLong(obj[0].toString()==null?"":obj[0].toString()));
			retorna.setPremPremio(obj[1].toString()==null?"":obj[1].toString());
			retorna.setPremStatus(Integer.parseInt(obj[2].toString()==null?"":obj[2].toString()));
			retorna.setStprId(Long.parseLong(obj[3].toString()==null?"":obj[3].toString()));
			retorna.setStprStatus(obj[4].toString()==null?"":obj[4].toString());
		}
		return retorna;
	}	

}

class obterPremios{
	private Long premId;
	private String premPremio;
	private int premStatus;
	private Long stprId;
	private String stprStatus;
	
	public Long getPremId() {
		return premId;
	}
	public void setPremId(Long premId) {
		this.premId = premId;
	}
	public String getPremPremio() {
		return premPremio;
	}
	public void setPremPremio(String premPremio) {
		this.premPremio = premPremio;
	}
	public int getPremStatus() {
		return premStatus;
	}
	public void setPremStatus(int premStatus) {
		this.premStatus = premStatus;
	}
	public Long getStprId() {
		return stprId;
	}
	public void setStprId(Long stprId) {
		this.stprId = stprId;
	}
	public String getStprStatus() {
		return stprStatus;
	}
	public void setStprStatus(String stprStatus) {
		this.stprStatus = stprStatus;
	}
}
