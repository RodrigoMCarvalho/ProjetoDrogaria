package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Venda;
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
	
}
