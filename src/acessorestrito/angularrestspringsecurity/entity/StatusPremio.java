package acessorestrito.angularrestspringsecurity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the status_premio database table.
 * 
 */
@javax.persistence.Entity
@Table(name="status_premio", schema = "sistema")
@NamedQuery(name="StatusPremio.findAll", query="SELECT s FROM StatusPremio s")
public class StatusPremio implements Entity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="stpr_id")
	private Integer stprId;

	@Column(name="stpr_status")
	private String stprStatus;

	public StatusPremio() {
	}

	public Integer getStprId() {
		return this.stprId;
	}

	public void setStprId(Integer stprId) {
		this.stprId = stprId;
	}

	public String getStprStatus() {
		return this.stprStatus;
	}

	public void setStprStatus(String stprStatus) {
		this.stprStatus = stprStatus;
	}

}