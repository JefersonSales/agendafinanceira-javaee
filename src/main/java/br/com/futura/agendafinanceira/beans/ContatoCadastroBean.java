package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Contato;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.services.ContatoService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class ContatoCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Fornecedor fornecedor;

	private Contato contato;
	
	@Inject
	private ContatoService contatoService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.contato = new Contato();
		this.contato.setAtivo(Ativo.ATIVO);
	}

	public String voltar() {
		return "/fornecedorcadastro.xhtml?fornecedor=" + this.fornecedor.getIdFornecedor();
	}

	public String salvar() {
		contatoService.salvar(contato);
		messagesHelper.addFlash(new FacesMessage("Operação concluida com sucesso."));
		return "/fornecedorcadastro?faces-redirect=true&fornecedor=" + contato.getFornecedor().getIdFornecedor(); 
	}

	public String excluir(Contato contato) {
		Integer fornecedorId = contato.getFornecedor().getIdFornecedor();
		contatoService.excluir(contato);
		contato.getFornecedor().removeContato(contato);
		messagesHelper.addFlash(new FacesMessage("Operação concluida com sucesso."));
		return "/fornecedorcadastro?faces-redirect=true&fornecedor=" + fornecedorId; 
	}

	public Contato getContato() {
		if (contato == null) {
			init();
		}
		this.contato.setFornecedor(this.fornecedor);
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
