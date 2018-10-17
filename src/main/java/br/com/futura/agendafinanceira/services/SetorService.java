package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.SetorDao;
import br.com.futura.agendafinanceira.models.Setor;

public class SetorService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SetorDao setorDao;

	public List<Setor> listarTodos() {
		return setorDao.listarTodos();
	}

	public List<Setor> listarPorDescricao(String pesquisaDescricao) {
		return setorDao.listarPorDescricao(pesquisaDescricao);
	}

	public void excluir(Setor setor) {
		setorDao.excluir(setor);
	}

	public void salvar(Setor setor) {
		setorDao.salvar(setor);
	}

}