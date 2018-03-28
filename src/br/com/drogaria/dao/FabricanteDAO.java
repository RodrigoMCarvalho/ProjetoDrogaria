package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.HibernateUtil;

public class FabricanteDAO {

	// método para salvar no DB
	public void salvar(Fabricante fabricante) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();// captura a fábrica de sessões
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(fabricante);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}

	// método para listar
	@SuppressWarnings("unchecked")
	public List<Fabricante> listar() { // retorna uma lista de fabricantes

		Session sessao = HibernateUtil.getSessionFactory().openSession(); // inicia a fábrica de conexões
		List<Fabricante> listFabricantes = null; // inicia uma lista de fabricantes

		try {
			Query consulta = sessao.getNamedQuery("Fabricante.listar"); // cria a query conforme @NamedQuery em
																		// FabricanteDAO
			listFabricantes = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return listFabricantes;
	}

	public Fabricante buscarPorCodigo(Long cod) { // retorna apenas um fabricante
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Fabricante fabricante = null;
		try {
			Query consulta = sessao.getNamedQuery("Fabricante.buscarPorCodigo");
			consulta.setLong("codigo", cod);
			fabricante = (Fabricante) consulta.uniqueResult(); // retorna apenas um resultado
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return fabricante;
	}
	
	//método para editar o fabricante
	public void editar(Fabricante fab) {   //recebe o código e a descrição
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			Fabricante fabricanteEditar = buscarPorCodigo(fab.getCodigo()); // mantém o codigo obtido em fab
			fabricanteEditar.setDescricao(fab.getDescricao()); // altera a descrição obtida em fab
			
			sessao.update(fabricanteEditar);
			transacao.commit();
			
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
	}

	// método de exclusão por fabricante
	public void remover(Fabricante fabricante) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(fabricante);
			transacao.commit();
		} catch (RuntimeException e) {
			if (sessao != null) { // se ocorrer uma Exception e a sessão nao estiver vazia, faz um rollback
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}


}
