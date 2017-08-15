package acessorestrito.angularrestspringsecurity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the informacoes database table.
 * 
 */
@javax.persistence.Entity
@Table(name="informacoes", schema = "sistema")
@NamedQuery(name="Informacoe.findAll", query="SELECT i FROM Informacoes i")
public class Informacoes implements Entity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="info_id")
	private Integer infoId;

	@Column(name="info_descricao")
	private String infoDescricao;

	@Column(name="info_item")
	private String infoItem;

	@Column(name="info_titulo")
	private String infoTitulo;

	@Column(name="info_valor")
	private String infoValor;

	public Informacoes() {
	}

	public Integer getInfoId() {
		return this.infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getInfoDescricao() {
		return this.infoDescricao;
	}

	public void setInfoDescricao(String infoDescricao) {
		this.infoDescricao = infoDescricao;
	}

	public String getInfoItem() {
		return this.infoItem;
	}

	public void setInfoItem(String infoItem) {
		this.infoItem = infoItem;
	}

	public String getInfoTitulo() {
		return this.infoTitulo;
	}

	public void setInfoTitulo(String infoTitulo) {
		this.infoTitulo = infoTitulo;
	}

	public String getInfoValor() {
		return this.infoValor;
	}

	public void setInfoValor(String infoValor) {
		this.infoValor = infoValor;
	}

}