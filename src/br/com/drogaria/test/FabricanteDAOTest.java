package br.com.drogaria.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Fabricante f1 = new Fabricante();
		FabricanteDAO dao = new FabricanteDAO();

		f1.setDescricao("Renault");
		dao.salvar(f1);
	}

	@Test
	@Ignore
	public void listar() {
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar();

		for (Fabricante fabricante : fabricantes) {
			System.out.println(fabricante);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FabricanteDAO dao = new FabricanteDAO();

		Fabricante f1 = dao.buscarPorCodigo(1L);
		Fabricante f2 = dao.buscarPorCodigo(3L);

		System.out.println(f1);
		System.out.println(f2);
	}

	@Test
	@Ignore
	public void remover() {
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscarPorCodigo(9L);

		dao.remover(fabricante);
	}
	
	@Test
	//@Ignore
	public void editar() {
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante f1 = new Fabricante();
		
		f1.setCodigo(3L);
		f1.setDescricao("Ceperatin");
		
		dao.editar(f1);
		
		listar();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
