package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.FornecedorDao;
import br.com.futura.agendafinanceira.models.Fornecedor;

public class FornecedorService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FornecedorDao fornecedorDao;

	public List<Fornecedor> listarPor(String filtro) {
		return fornecedorDao.listarPor(filtro);
	}

	public List<Fornecedor> listarTodos() {
		return fornecedorDao.listarTodos();
	}

	public void excluir(Fornecedor fornecedor) {
		fornecedorDao.excluir(fornecedor);
	}
	
	public void salvar(Fornecedor fornecedor) {
		fornecedorDao.salvar(fornecedor);
	}
	

}
