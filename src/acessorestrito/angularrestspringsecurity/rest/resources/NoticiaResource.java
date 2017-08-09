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
import acessorestrito.angularrestspringsecurity.dao.noticia.NoticiaDao;
import acessorestrito.angularrestspringsecurity.dao.user.UserDao;
import acessorestrito.angularrestspringsecurity.entity.AccessToken;
import acessorestrito.angularrestspringsecurity.entity.Noticia;
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
@Path("/noticia")
public class NoticiaResource {

	@Autowired
	private NoticiaDao noticiaDaoInterface;

	private NoticiaDao noticiaDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public  List<Noticia> listarNoticia() {
		
		List<Noticia> listarNoticia = this.noticiaDaoInterface.findAll();
		
		return listarNoticia;
	}

	
	@Path("Cadastrar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Noticia Cadastrar(Noticia noticia) {
		
		return this.noticiaDaoInterface.save(noticia);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/alteracao")
	public Noticia Alterar(@QueryParam("id") Integer id) {
	//System.out.println("xvideo"+id);
	return this.noticiaDaoInterface.find(id);
}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/alterar")
	public Noticia Alteracao(Noticia noticia) {
	
	return this.noticiaDaoInterface.save(noticia);
}


	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public void delete(@QueryParam("id") Integer id) {
		this.noticiaDaoInterface.delete(id);
	}

}
