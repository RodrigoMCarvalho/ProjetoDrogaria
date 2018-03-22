package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Item;
import br.com.drogaria.util.HibernateUtil;

public class ItemDAO {

	public void salvar(Item item) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(item);
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
	public List<Item> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Item> itens = null;
		try {
			Query consulta = sessao.getNamedQuery("Item.listar");
			itens = consulta.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		return itens;
	}

	public Item buscarPorCod(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Item item = null;
		try {
			Query consulta = sessao.getNamedQuery("Item.buscarPorCod");
			consulta.setLong("codigo", codigo);
			item = (Item) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		return item;
	}

	public void remover(Item item) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(item);
			transacao.commit();
		} catch (RuntimeException e) {
			if (sessao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}

}
