package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.services.ContaService;
import br.com.futura.agendafinanceira.services.FornecedorService;
import br.com.futura.agendafinanceira.services.PagamentoParcelaService;
import br.com.futura.agendafinanceira.services.PagamentoService;
import br.com.futura.agendafinanceira.services.SetorService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class PagamentoCadastroBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Pagamento pagamento;
	
	private List<PagamentoParcela> parcelas;
	
	private List<Setor> setores;
	
	private List<Conta> contas;
	
	private List<Fornecedor> fornecedores;
 	
	@Inject
	private SetorService setorService;
	
	@Inject
	private ContaService contaService;
	
	@Inject
	private FornecedorService fornecedorService;
	
	@Inject
	private PagamentoService pagamentoService;
	
	@Inject
	private PagamentoParcelaService parcelaService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.setores = setorService.listarTodos();
		this.contas = contaService.listarTodos();
		this.fornecedores = fornecedorService.listarTodos();
	}
	
	public String salvar() {
		pagamentoService.salvar(pagamento);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		return "/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}
	
	public String excluir(PagamentoParcela parcela){
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		parcelaService.excluir(pagamento, parcela);
		return "/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}
	
	public boolean habilitarParcelas() {
		return this.pagamento.getIdPagamento() != null;
	}
	
	public Pagamento getPagamento() {
		if (this.pagamento == null){
			this.pagamento = new Pagamento();
		}
		return pagamento;
	}
	
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public List<PagamentoParcela> getParcelas() {
		return parcelas;
	}
	
	public List<Setor> getSetores() {
		return setores;
	}
	
	public List<Conta> getContas() {
		return contas;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

}
