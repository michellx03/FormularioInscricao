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

import Uteis.DadosUsuarioEmail;
import Uteis.EnviarEmail;
//import Uteis.Conversores;
//import Uteis.DadosUsuarioEmail;
//import Uteis.EnviarEmail;
import acessorestrito.angularrestspringsecurity.JsonViews;
import acessorestrito.angularrestspringsecurity.dao.JpaDao;
import acessorestrito.angularrestspringsecurity.dao.inscrito.InscritoDao;
import acessorestrito.angularrestspringsecurity.entity.AccessToken;
import acessorestrito.angularrestspringsecurity.entity.Inscrito;
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
@Path("/inscrito")
public class InscritoResource {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Autowired
	private InscritoDao inscritoDaoInterface;

	private InscritoDao inscritoDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public  List<Inscrito> listarInscrito() {
		
		List<Inscrito> listarInscrito = this.inscritoDaoInterface.findAll();
		
		return listarInscrito;
	}

	
	@Path("Cadastrar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Inscrito Cadastrar(Inscrito inscrito) {
		DadosUsuarioEmail email = new DadosUsuarioEmail();
		email.setEmail(inscrito.getInscEmail());
		email.setName(inscrito.getInscNomeCompleto());
		email.setSubject("Plano - Classi Vale");
		email.setMessage("Ola! "+inscrito.getInscNomeCompleto()+"\nInscricao realizada com sucesso!");
			
		EnviarEmail enviar = new EnviarEmail();
		enviar.enviarEmail(email);
		
		return this.inscritoDaoInterface.save(inscrito);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/alteracao")
	public Inscrito Alterar(@QueryParam("id") Integer id) {

	return this.inscritoDaoInterface.find(id);
}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/alterar")
	public Inscrito Alteracao(Inscrito inscrito) {
	
	return this.inscritoDaoInterface.save(inscrito);
}


	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public void delete(@QueryParam("id") Integer id) {
		this.inscritoDaoInterface.delete(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/obterInscritos")
	public obterInscrito obterDados(@QueryParam("id") Integer id) {
		
		String select = "SELECT insc_id, insc_nome_completo, insc_rg, insc_cpf, insc_telefone, insc_celular, insc_cidade, insc_estado, insc_email, insc_numero_inscricao, "
						+"insc_stin_id, stin_status, prem_premio, insc_prem_id, insc_estudante FROM sistema.inscrito "
						+"INNER JOIN sistema.status_inscrito ON insc_stin_id = stin_id "
						+"INNER JOIN sistema.premio ON insc_prem_id = prem_id "
						+"WHERE insc_id ="+id+"";
		
		Query query = entityManager.createNativeQuery(select);
		List<Object[]> list = query.getResultList();
		
		obterInscrito retorna = new obterInscrito();
		
		for (Object[] obj : list) {
			//retornUsuario.setDausNomeCompleto(obj[0]==null?"":obj[0].toString())
			retorna.setInscId(Long.parseLong(obj[0].toString()==null?"":obj[0].toString()));
			retorna.setInscNomeCompleto(obj[1].toString()==null?"":obj[1].toString());
			retorna.setInscRg(obj[2].toString()==null?"":obj[2].toString());
			retorna.setInscCpf(obj[3].toString()==null?"":obj[3].toString());
			retorna.setInscTelefone(obj[4].toString()==null?"":obj[4].toString());
			retorna.setInscCelular(obj[5].toString()==null?"":obj[5].toString());
			retorna.setInscCidade(obj[6].toString()==null?"":obj[6].toString());
			retorna.setInscEstado(obj[7].toString()==null?"":obj[7].toString());
			retorna.setInscEmail(obj[8].toString()==null?"":obj[8].toString());
			retorna.setInscNumeroInscricao(Integer.parseInt(obj[9].toString()==null?"":obj[9].toString()));
			retorna.setInscStinId(Integer.parseInt(obj[10].toString()==null?"":obj[10].toString()));
			retorna.setStinStatus(obj[11].toString()==null?"":obj[11].toString());
			retorna.setPremPremio(obj[12].toString()==null?"":obj[12].toString());
			retorna.setInscPremId(Integer.parseInt(obj[13].toString()==null?"":obj[13].toString()));
			retorna.setStinEstudante(obj[14].toString()==null?"":obj[14].toString());
		}
		return retorna;
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/consulta")
	public obterInscrito obterInscrito() {
		
		String select = "SELECT insc_id, insc_nome_completo, insc_numero_inscricao, prem_premio FROM sistema.inscrito "
						+" INNER JOIN sistema.status_inscrito ON insc_stin_id = stin_id "
						+" INNER JOIN sistema.premio ON insc_prem_id = prem_id ";
		
		Query query = entityManager.createNativeQuery(select);
		List<Object[]> list = query.getResultList();
		
		obterInscrito retorna = new obterInscrito();
		
		for (Object[] obj : list) {
			retorna.setInscId(Long.parseLong(obj[0].toString()==null?"":obj[0].toString()));
			retorna.setInscNomeCompleto(obj[1].toString()==null?"":obj[1].toString());
			retorna.setInscNumeroInscricao(Integer.parseInt(obj[2].toString()==null?"":obj[2].toString()));
			retorna.setPremPremio(obj[3].toString()==null?"":obj[3].toString());
		}
		return retorna;
	}	

}

class obterInscrito{
	private Long inscId;
	private String inscNomeCompleto;
	private String inscRg;
	private String inscCpf;
	private String inscTelefone;
	private String inscCelular;
	private String inscCidade;
	private String inscEstado;
	private int inscPremId;
	private String premPremio;
	private String inscEmail;
	private int inscNumeroInscricao;
	private int inscStinId;
	private String stinStatus;
	private String stinEstudante;
	
	public String getStinEstudante() {
		return stinEstudante;
	}
	public void setStinEstudante(String stinEstudante) {
		this.stinEstudante = stinEstudante;
	}
	public Long getInscId() {
		return inscId;
	}
	public void setInscId(Long inscId) {
		this.inscId = inscId;
	}
	public String getInscNomeCompleto() {
		return inscNomeCompleto;
	}
	public void setInscNomeCompleto(String inscNomeCompleto) {
		this.inscNomeCompleto = inscNomeCompleto;
	}
	public String getInscRg() {
		return inscRg;
	}
	public void setInscRg(String inscRg) {
		this.inscRg = inscRg;
	}
	public String getInscCpf() {
		return inscCpf;
	}
	public void setInscCpf(String inscCpf) {
		this.inscCpf = inscCpf;
	}
	public String getInscTelefone() {
		return inscTelefone;
	}
	public void setInscTelefone(String inscTelefone) {
		this.inscTelefone = inscTelefone;
	}
	public String getInscCelular() {
		return inscCelular;
	}
	public void setInscCelular(String inscCelular) {
		this.inscCelular = inscCelular;
	}
	public String getInscCidade() {
		return inscCidade;
	}
	public void setInscCidade(String inscCidade) {
		this.inscCidade = inscCidade;
	}
	public String getInscEstado() {
		return inscEstado;
	}
	public void setInscEstado(String inscEstado) {
		this.inscEstado = inscEstado;
	}
	public int getInscPremId() {
		return inscPremId;
	}
	public void setInscPremId(int inscPremId) {
		this.inscPremId = inscPremId;
	}
	public String getPremPremio() {
		return premPremio;
	}
	public void setPremPremio(String premPremio) {
		this.premPremio = premPremio;
	}
	public String getInscEmail() {
		return inscEmail;
	}
	public void setInscEmail(String inscEmail) {
		this.inscEmail = inscEmail;
	}
	public int getInscNumeroInscricao() {
		return inscNumeroInscricao;
	}
	public void setInscNumeroInscricao(int inscNumeroInscricao) {
		this.inscNumeroInscricao = inscNumeroInscricao;
	}
	public int getInscStinId() {
		return inscStinId;
	}
	public void setInscStinId(int inscStinId) {
		this.inscStinId = inscStinId;
	}
	public String getStinStatus() {
		return stinStatus;
	}
	public void setStinStatus(String stinStatus) {
		this.stinStatus = stinStatus;
	}
}
