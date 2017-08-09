package acessorestrito.angularrestspringsecurity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the status_inscrito database table.
 * 
 */
@javax.persistence.Entity
@NamedQuery(name="StatusInscrito.findAll", query="SELECT s FROM StatusInscrito s")
@Table (name = "status_inscrito", schema = "sistema")
public class StatusInscrito implements Entity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="stin_id")
	@GeneratedValue
	private Integer stinId;

	@Column(name="stin_status")
	private String stinStatus;

	public StatusInscrito() {
	}

	public Integer getStinId() {
		return this.stinId;
	}

	public void setStinId(Integer stinId) {
		this.stinId = stinId;
	}

	public String getStinStatus() {
		return this.stinStatus;
	}

	public void setStinStatus(String stinStatus) {
		this.stinStatus = stinStatus;
	}

}