package acessorestrito.angularrestspringsecurity.rest.resources;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
//import Uteis.Conversores;
import acessorestrito.angularrestspringsecurity.JsonViews;
import acessorestrito.angularrestspringsecurity.dao.JpaDao;
import acessorestrito.angularrestspringsecurity.dao.inscrito.InscritoDao;
import acessorestrito.angularrestspringsecurity.dao.user.UserDao;
import acessorestrito.angularrestspringsecurity.entity.AccessToken;
import acessorestrito.angularrestspringsecurity.entity.Inscrito;
import acessorestrito.angularrestspringsecurity.entity.Usuario;
import acessorestrito.angularrestspringsecurity.entity.Role;
import acessorestrito.angularrestspringsecurity.service.UserService;
import acessorestrito.angularrestspringsecurity.transfer.UserTransfer;
import javassist.expr.NewArray;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.awt.print.Printable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Path("/inscrito")
public class InscritoResource {

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

}
