package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.HibernateUtil;

public class ProdutoDAO {
	Session sessao = HibernateUtil.getSessionFactory().openSession();
	
	public void salvar(Produto produto) {
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(produto);
			transacao.commit();
		} catch (Exception e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		List<Produto> produtos = null;
		try {
			Query consulta = sessao.getNamedQuery("Produto.listar");
			produtos = consulta.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		return produtos;
	}
	
	public Produto buscarPorCod(Long codigo){
		Produto produto = null;
		try {
			Query consulta = sessao.getNamedQuery("Produto.buscarPorCod");
			consulta.setLong("codigo", codigo);
			produto = (Produto) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		return produto;
	}
	
	public void excluir(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(produto);
			transacao.commit();
		} catch (Exception e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		}finally {
			sessao.close();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
