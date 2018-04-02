package br.com.drogaria.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;

public class FuncionarioDAOTest {
	@Test
	@Ignore
	public void salvar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 = new Funcionario();
		
		f1.setNome("teste");
		f1.setFuncao("Assistente");
		f1.setCpf("111155557");
		f1.setSenha("1234");
		
		dao.salvar(f1);
	}
	
	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> listFunc =  dao.listar();
		
		for (Funcionario funcionario : listFunc) {
			System.out.println(funcionario);
		}
	}
	
	@Test
	@Ignore
	public void buscarCod() {
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario f1 = dao.buscarPorCod(4L);
		
		System.out.println(f1);
	}
	
	@Test
	@Ignore
	public void editar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 =  new Funcionario();
		
		f1.setCodigo(2L);
		f1.setNome("Gustavo");
		f1.setFuncao("Assistente");
		f1.setCpf("111155555");
		f1.setSenha("1234");
		
		dao.editar(f1);
	}
	
	@Test
	@Ignore
	public void excluir() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 =  new Funcionario();

		f1 = dao.buscarPorCod(4L);
		
		dao.excluir(f1);
		
	}
	
	@Test
	public void autenticacao() {
		FuncionarioDAO dao =  new FuncionarioDAO();
		Funcionario f1 = new Funcionario();
		
		f1 = dao.autenticacao("440.463.942-26", "12345678");
		
		System.out.println("Funcionario: " + f1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
