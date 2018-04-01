package br.com.drogaria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {
	private List<Produto> listProdutos;
	private List<Produto> listProdutosFiltrados;
	private Venda vendaCadastro;	//valor total
	
	private List<Item> listItens; //carrinho de compras

	public void carregarProdutos() {

		try {
			ProdutoDAO dao = new ProdutoDAO();
			listProdutos = dao.listar();
		} catch (Exception e) {
			FacesUtil.addMsgError("Falha ao carregar lista de funcionarios: " + e.getMessage());
		}
	}

	public void adicionar(Produto produto) {
		int posicaoEncontrada = -1; //marcar posição do item encontrado

		for (int posicao = 0; posicao < listItens.size() && posicaoEncontrada < 0; posicao++) {

			Item itemTemp = listItens.get(posicao);

			if (itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = posicao;
			}
		}
		Item item = new Item();
		item.setProduto(produto);

		if (posicaoEncontrada < 0) {  //adicionando um produto novo no carrinho
			item.setQuantidade(1);
			item.setValor(produto.getPreco());
			listItens.add(item);
		} else {
			Item itemTemp = listItens.get(posicaoEncontrada);    //editando um produto ja existente no carrinho
			item.setQuantidade(itemTemp.getQuantidade() + 1);
			item.setValor(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			// para realizar a multiplicação entre BigDecimal e Interger
			listItens.set(posicaoEncontrada, item);
			// add adciona na última posição, set adiciona no index desejado
		}
		//valor total
		vendaCadastro.setValor(vendaCadastro.getValor().add(produto.getPreco())); //soma entre BigDecimais
	}
	
	public void remover(Item item) {
		int posicaoEncontrada = -1;
		for (int posicao = 0; posicao < listItens.size() && posicaoEncontrada < 0; posicao++) {
			Item itemTemp = listItens.get(posicao);
			if (itemTemp.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = posicao;
			}
		}
		if (posicaoEncontrada > -1) {
			listItens.remove(posicaoEncontrada);
			//valor total
			vendaCadastro.setValor(vendaCadastro.getValor().subtract(item.getValor())); //subtração entre BigDecimais
		}
	}
	
	//mostrar os dados no dialog, na finalização da venda
	public void carregarDadosVenda() {
		vendaCadastro.setHorario(new Date());
		
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 = dao.buscarPorCod(2L);
		vendaCadastro.setFuncionario(f1);
	}
	
	public void salvar() {
		try {
			VendaDAO vdao = new VendaDAO();
			
			Long codVenda = vdao.salvar(vendaCadastro);  //referente ao código da venda
			Venda vendaFK = vdao.buscarPorCod(codVenda); //com o vendaFK recupera a venda feita
			for (Item item : listItens) { //para cada item da lista de itens 
				item.setVenda(vendaFK);   //seta a chave estrangeira
				ItemDAO idao = new ItemDAO();
				idao.salvar(item);    //salva o item no BD
			}
			
			vendaCadastro = new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00"));   //para zerar o valor total após finalizar
			listItens = new ArrayList<>();        //para zerar o carrinho após finalizar
			
			FacesUtil.addMsgInfo("Venda realizada com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro para salvar: "+e.getMessage());
		}
		
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

	public Venda getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00")); //para evitar nullpoint no valor total
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

}
