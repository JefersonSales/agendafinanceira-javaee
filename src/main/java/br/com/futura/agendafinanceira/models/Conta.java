package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the contas database table.
 * 
 */
@Entity
@Table(name="contas")
@NamedQuery(name="Conta.findAll", query="SELECT c FROM Conta c")
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_conta")
	private int idConta;

	private byte ativo;

	private Timestamp atualizacao;

	private String descricao;

	//bi-directional many-to-one association to Pgto
	@OneToMany(mappedBy="conta")
	private List<Pgto> pgtos;

	public Conta() {
	}

	public int getIdConta() {
		return this.idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}

	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
		this.ativo = ativo;
	}

	public Timestamp getAtualizacao() {
		return this.atualizacao;
	}

	public void setAtualizacao(Timestamp atualizacao) {
		this.atualizacao = atualizacao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Pgto> getPgtos() {
		return this.pgtos;
	}

	public void setPgtos(List<Pgto> pgtos) {
		this.pgtos = pgtos;
	}

	public Pgto addPgto(Pgto pgto) {
		getPgtos().add(pgto);
		pgto.setConta(this);

		return pgto;
	}

	public Pgto removePgto(Pgto pgto) {
		getPgtos().remove(pgto);
		pgto.setConta(null);

		return pgto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idConta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (idConta != other.idConta)
			return false;
		return true;
	}

}