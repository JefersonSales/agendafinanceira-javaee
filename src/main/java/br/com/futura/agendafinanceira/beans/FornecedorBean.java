package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.FornecedorDao;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Fornecedor> fornecedores;
	
	private String pesquisa;
	
	@Inject
	private FornecedorDao fornecedorDao;
	
	@Inject
	private MessagesHelper messagesHelper;
	
	@PostConstruct
	private void init(){
		this.fornecedores = fornecedorDao.listarTodos();
	}
	
	public void pesquisar(){
		messagesHelper.addFlash(new FacesMessage("Em Desenvolvimento...."));
	}
	
	public void alterar(){
		messagesHelper.addFlash(new FacesMessage("Em Desenvolvimento...."));
		
	}
	
	public void excluir(){
		messagesHelper.addFlash(new FacesMessage("Em Desenvolvimento...."));
		
	}
	
	public String getPesquisa() {
		return pesquisa;
	}
	
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	

}
