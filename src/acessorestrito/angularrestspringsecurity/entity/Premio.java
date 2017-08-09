package acessorestrito.angularrestspringsecurity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the premio database table.
 * 
 */
@javax.persistence.Entity
@NamedQuery(name="Premio.findAll", query="SELECT p FROM Premio p")
@Table (name = "premio", schema = "sistema")
public class Premio implements Entity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="prem_id")
	@GeneratedValue
	private Integer premId;

	@Column(name="prem_premio")
	private String premPremio;

	public Premio() {
	}

	public Integer getPremId() {
		return this.premId;
	}

	public void setPremId(Integer premId) {
		this.premId = premId;
	}

	public String getPremPremio() {
		return this.premPremio;
	}

	public void setPremPremio(String premPremio) {
		this.premPremio = premPremio;
	}

}