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
import br.com.futura.agendafinanceira.models.enums.TipoPessoa;
import br.com.futura.agendafinanceira.services.FornecedorService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class FornecedorCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor;

	private String documentoMascara;

	private String documentoRotulo;

	@Inject
	private FornecedorService fornecedorService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.fornecedor = new Fornecedor();
		this.fornecedor.setAtivo(Ativo.ATIVO);
		this.fornecedor.setPfPj(TipoPessoa.PJ);
	}
	
	public boolean isCadastrarContatos() {
		return this.fornecedor.getIdFornecedor() != null;
	}
	
	public String voltar() {
		if (this.fornecedor.getIdFornecedor()==null){
			return "/fornecedorcadastro.xhtml";
		}else{
			return "/fornecedorcadastro.xhtml?fornecedor=" + this.fornecedor.getIdFornecedor();
		}
	}

	public String salvar() {
		fornecedorService.salvar(fornecedor);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		return "/fornecedorcadastro?faces-redirect=true&fornecedor=" + fornecedor.getIdFornecedor();
	}

	public String alterarContato(Contato contato) {
		return "/contatocadastro?faces-redirect=true&"
				+ "fornecedor=" + contato.getFornecedor().getIdFornecedor()
				+ "&contato=" + contato.getIdContato();
	}
	
	public String novoContato(){
		return "/contatocadastro?faces-redirect=true&"
				+ "fornecedor=" + this.fornecedor.getIdFornecedor();
	}
		

	public void definirDocumento() {
		this.documentoMascara = ((TipoPessoa) this.fornecedor.getPfPj()).getMascara();
		this.documentoRotulo = ((TipoPessoa) this.fornecedor.getPfPj()).getRotulo();
	}

	public Fornecedor getFornecedor() {
		if (this.fornecedor == null) {
			init();
		}
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public TipoPessoa[] getTipoPessoas() {
		return TipoPessoa.values();
	}

	public String getDocumentoMascara() {
		return documentoMascara;
	}

	public String getDocumentoRotulo() {
		return documentoRotulo;
	}
	
	public boolean isPermiteCadastrarContato(){
		return (this.fornecedor.getIdFornecedor()!=null); 
	}

}
