package br.com.drogaria.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fdao = new FabricanteDAO();
		Fabricante fab = fdao.buscarPorCodigo(3L);

		Produto produto = new Produto();
		produto.setDescricao("Sandeiro");
		produto.setPreco(new BigDecimal(31.00D));
		produto.setQuantidade(3);
		produto.setFabricante(fab);

		ProdutoDAO pdao = new ProdutoDAO();
		pdao.salvar(produto);
	}

	@Test
	@Ignore
	public void buscarPorCod() {
		ProdutoDAO pdao = new ProdutoDAO();
		Produto p = pdao.buscarPorCod(3L);

		System.out.println(p);
	}
	
	@Test
	//@Ignore
	public void listar() {
		ProdutoDAO pdao = new ProdutoDAO();
		
		List<Produto> produtos = pdao.listar();
		
		for (Produto p : produtos) {
			System.out.println(p);
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		ProdutoDAO pdao = new ProdutoDAO();
		Produto p = pdao.buscarPorCod(3L);
		
		pdao.excluir(p);		
	}
	
	@Test
	@Ignore
	public void editar() {
		ProdutoDAO pdao = new ProdutoDAO();
		Produto p = pdao.buscarPorCod(4L);
		
		p.setDescricao("Novalgina");
		p.setPreco(new BigDecimal(4.50D));
		p.setQuantidade(23);
		
		FabricanteDAO fdao = new FabricanteDAO();
		Fabricante f1 = fdao.buscarPorCodigo(3L);
		f1.setDescricao("TELETAN");
		
		p.setFabricante(f1);
		
		pdao.editar(p);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
