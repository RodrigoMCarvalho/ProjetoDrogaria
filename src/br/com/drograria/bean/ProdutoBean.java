package br.com.drograria.bean;

import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	private Produto produtoCadastro;
	private List<Produto> listProdutos;
	private List<Produto> listProdutosFiltrados;
	private String acao; // irá guardar 'Novo', 'Editar', 'Excluir'
	private Long codigo;
	private List<Fabricante> listFabricantes;
	

	public void salvar() { // try catch receberá informações da Classe DAO
		try {

			ProdutoDAO dao = new ProdutoDAO();
			dao.salvar(produtoCadastro);
			FacesUtil.addMsgInfo("Produto cadastrado com sucesso!"); // ***usando o GROWL e classe FacesUtil
			produtoCadastro = new Produto();
			// FacesContext.getCurrentInstance().addMessage(null, new
			// FacesMessage(FacesMessage.SEVERITY_INFO,
			// "Informação: ", "Funcionário salvo com sucesso!")); //*** usando o Message
			// ***
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro para salvar o produto: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(produtoCadastro);
			FacesUtil.addMsgInfo("Produto excluído com sucesso!");
			produtoCadastro = new Produto();
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro para excluir o produto: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.editar(produtoCadastro);
			FacesUtil.addMsgInfo("Produto alterado com sucesso!");
			produtoCadastro = new Produto();
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro para editar o produto: " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			listProdutos = dao.listar();
		} catch (Exception e) {
			FacesUtil.addMsgError("Falha ao carregar lista de funcionarios: " + e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				ProdutoDAO dao = new ProdutoDAO();
				produtoCadastro = dao.buscarPorCod(codigo);
			} else {
				produtoCadastro = new Produto();
			}
			
			FabricanteDAO fdao = new FabricanteDAO();  //para carregar o fabricantes no produtoCadastro.xhtml
			listFabricantes = fdao.listar();
			
		} catch (Exception e) {
			FacesUtil.addMsgError("Falha ao tentar carregar o funcionário: " + e.getMessage());
		}
	}

	public Produto getProdutoCadastro() {
		if (produtoCadastro == null) {
			produtoCadastro = new Produto();
			// necessário para evitar o erro "javax.el.PropertyNotFoundException:
			// Target Unreachable, [fabricanteCadastro] returned null"
		}
		
		return produtoCadastro;
	}

	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<Produto> listProdutos) {
		this.listProdutos = listProdutos;
	}

	public List<Produto> getListProdutosFiltrados() {
		return listProdutosFiltrados;
	}

	public void setListProdutosFiltrados(List<Produto> listProdutosFiltrados) {
		this.listProdutosFiltrados = listProdutosFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<Fabricante> getListFabricantes() {
		return listFabricantes;
	}

	public void setListFabricantes(List<Fabricante> listFabricantes) {
		this.listFabricantes = listFabricantes;
	}
	
}
