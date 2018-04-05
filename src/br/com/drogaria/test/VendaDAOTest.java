package br.com.drogaria.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.filter.VendaFilter;

public class VendaDAOTest {
	@Test
	@Ignore
	public void salvar() {
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario f1 = fdao.buscarPorCod(1L);

		Venda venda = new Venda();
		venda.setFuncionario(f1);
		venda.setHorario(new Date());
		venda.setValor(new BigDecimal(168.99D));

		VendaDAO vdao = new VendaDAO();
		vdao.salvar(venda);
	}

	@Test
	@Ignore
	public void listar() {
		VendaDAO vdao = new VendaDAO();
		List<Venda> vendas = vdao.listar();
		for (Venda venda : vendas) {
			System.out.println(venda);
		}
	}
	
	@Test
	@Ignore
	public void buscar() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		VendaFilter filtro = new VendaFilter();
		filtro.setDataInicial(formato.parse("01/04/2018"));
		filtro.setDataFinal(formato.parse("04/04/2018"));
		
		VendaDAO dao = new VendaDAO();
		List<Venda> vendas = dao.buscar(filtro);
		
		for (Venda venda : vendas) {
			System.out.println(venda);
		}
	}
}	
	
	
	


	
	
	
	
	
	
	


