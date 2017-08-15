package acessorestrito.angularrestspringsecurity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inscrito database table.
 * 
 */
@javax.persistence.Entity
@NamedQuery(name="Inscrito.findAll", query="SELECT i FROM Inscrito i")
@Table (name = "inscrito", schema = "sistema")
public class Inscrito implements Entity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="insc_id")
	@GeneratedValue
	private Integer inscId;

	@Column(name="insc_celular")
	private String inscCelular;

	@Column(name="insc_cidade")
	private String inscCidade;

	@Column(name="insc_cpf")
	private String inscCpf;

	@Column(name="insc_email")
	private String inscEmail;

	@Column(name="insc_estado")
	private String inscEstado;

	@Column(name="insc_estudante")
	private String inscEstudante;

	@Column(name="insc_nome_completo")
	private String inscNomeCompleto;

	@Column(name="insc_numero_inscricao")
	private Integer inscNumeroInscricao;

	@Column(name="insc_prem_id")
	private Integer inscPremId;

	@Column(name="insc_rg")
	private String inscRg;

	@Column(name="insc_stin_id")
	private Integer inscStinId;

	@Column(name="insc_telefone")
	private String inscTelefone;

	public Inscrito() {
	}

	public Integer getInscId() {
		return this.inscId;
	}

	public void setInscId(Integer inscId) {
		this.inscId = inscId;
	}

	public String getInscCelular() {
		return this.inscCelular;
	}

	public void setInscCelular(String inscCelular) {
		this.inscCelular = inscCelular;
	}

	public String getInscCidade() {
		return this.inscCidade;
	}

	public void setInscCidade(String inscCidade) {
		this.inscCidade = inscCidade;
	}

	public String getInscCpf() {
		return this.inscCpf;
	}

	public void setInscCpf(String inscCpf) {
		this.inscCpf = inscCpf;
	}

	public String getInscEmail() {
		return this.inscEmail;
	}

	public void setInscEmail(String inscEmail) {
		this.inscEmail = inscEmail;
	}

	public String getInscEstado() {
		return this.inscEstado;
	}

	public void setInscEstado(String inscEstado) {
		this.inscEstado = inscEstado;
	}

	public String getInscEstudante() {
		return this.inscEstudante;
	}

	public void setInscEstudante(String inscEstudante) {
		this.inscEstudante = inscEstudante;
	}

	public String getInscNomeCompleto() {
		return this.inscNomeCompleto;
	}

	public void setInscNomeCompleto(String inscNomeCompleto) {
		this.inscNomeCompleto = inscNomeCompleto;
	}

	public Integer getInscNumeroInscricao() {
		return this.inscNumeroInscricao;
	}

	public void setInscNumeroInscricao(Integer inscNumeroInscricao) {
		this.inscNumeroInscricao = inscNumeroInscricao;
	}

	public Integer getInscPremId() {
		return this.inscPremId;
	}

	public void setInscPremId(Integer inscPremId) {
		this.inscPremId = inscPremId;
	}

	public String getInscRg() {
		return this.inscRg;
	}

	public void setInscRg(String inscRg) {
		this.inscRg = inscRg;
	}

	public Integer getInscStinId() {
		return this.inscStinId;
	}

	public void setInscStinId(Integer inscStinId) {
		this.inscStinId = inscStinId;
	}

	public String getInscTelefone() {
		return this.inscTelefone;
	}

	public void setInscTelefone(String inscTelefone) {
		this.inscTelefone = inscTelefone;
	}

}