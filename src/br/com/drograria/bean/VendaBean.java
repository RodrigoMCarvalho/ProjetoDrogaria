package br.com.drograria.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {
	private List<Produto> listProdutos;
	private List<Produto> listProdutosFiltrados;
	private List<Item> listItens;
	
	
	public void carregarProdutos() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			listProdutos = dao.listar();
		} catch (Exception e) {
			FacesUtil.addMsgError("Falha ao carregar lista de funcionarios: " + e.getMessage());
		}
	}
	
	public void adicionar(Produto produto) {
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidade(1);
		item.setValor(produto.getPreco());
		
		listItens.add(item);
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

	public List<Item> getListItens() {
		if (listItens == null) {
			listItens = new ArrayList<>();
		}
		return listItens;
	}

	public void setListItens(List<Item> listItens) {
		this.listItens = listItens;
	}
	
	
	
	
}
