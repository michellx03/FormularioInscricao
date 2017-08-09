package acessorestrito.angularrestspringsecurity.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the noticias database table.
 * 
 */
@javax.persistence.Entity
@NamedQuery(name="Noticia.findAll", query="SELECT n FROM Noticia n")
@Table (name = "noticias", schema = "sistema")
public class Noticia implements Entity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="noti_id")
	@GeneratedValue
	private Integer notiId;

	@Column(name="noti_data_hora_registro")
	private Timestamp notiDataHoraRegistro;

	@Column(name="noti_foto")
	private String notiFoto;

	@Column(name="noti_noticia")
	private String notiNoticia;

	public Noticia() {
	}

	public Integer getNotiId() {
		return this.notiId;
	}

	public void setNotiId(Integer notiId) {
		this.notiId = notiId;
	}

	public Timestamp getNotiDataHoraRegistro() {
		return this.notiDataHoraRegistro;
	}

	public void setNotiDataHoraRegistro(Timestamp notiDataHoraRegistro) {
		this.notiDataHoraRegistro = notiDataHoraRegistro;
	}

	public String getNotiFoto() {
		return this.notiFoto;
	}

	public void setNotiFoto(String notiFoto) {
		this.notiFoto = notiFoto;
	}

	public String getNotiNoticia() {
		return this.notiNoticia;
	}

	public void setNotiNoticia(String notiNoticia) {
		this.notiNoticia = notiNoticia;
	}

}