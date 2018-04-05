package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Venda;
import br.com.drogaria.filter.VendaFilter;
import br.com.drogaria.util.HibernateUtil;

public class VendaDAO {
	Session sessao = HibernateUtil.getSessionFactory().openSession();
	
	public Long salvar(Venda venda) {  //retornar um Long referente ao código da venda
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		
		try {
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(venda);  //salvar a venda e capturar o código da venda
			transacao.commit();
		} catch (Exception e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		}finally {
			sessao.close();
		}
		return codigo;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Venda> listar() {
		List<Venda> vendas  = null;
		try {
			Query consulta = sessao.getNamedQuery("Venda.listar");
			vendas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return vendas;
	}

	public void editar(Venda venda) {
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(venda);
			transacao.commit();
		}catch (Exception e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	public Venda buscarPorCod(Long codigo) {
		Venda v1 = null;
		try {
			Query consulta = sessao.getNamedQuery("Venda.buscarPorCodigo");
			consulta.setLong("codigo", codigo); //"codigo" é referente a =:codigo em @NamedQuery
			v1 = (Venda) consulta.uniqueResult();  //retorna apenas uma unica consulta
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		return v1;
	}
	
	public void excluir(Venda venda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(venda);
			transacao.commit();
		}catch (Exception e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Venda> buscar(VendaFilter filtro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Venda> vendas = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT venda FROM Venda venda "); //espaço no final para concatenar com o comando abaixo
		if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) { //Se o usuário informou a data inicial e final
			sql.append("WHERE venda.horario BETWEEN :dataInicial AND :dataFinal ");
		}
		sql.append("ORDER BY venda.horario ");
		
		try {
			Query consulta = sessao.createQuery(sql.toString());
			if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
				consulta.setDate("dataInicial", filtro.getDataInicial());
				consulta.setDate("dataFinal", filtro.getDataFinal());
			}
			vendas = consulta.list();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			sessao.close();
		}
		return vendas;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
