package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.HibernateUtil;

public class FuncionarioDAO {
	Session sessao = HibernateUtil.getSessionFactory().openSession();
	Transaction transacao = null;

	public void salvar(Funcionario funcionario) {

		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcionario);
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
	public List<Funcionario> listar() {
		List<Funcionario> listFuncionarios = null;
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.listar");
			listFuncionarios = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return listFuncionarios;
	}

	public void editar(Funcionario funcionario) {
		try {
			transacao = sessao.beginTransaction();
			sessao.update(funcionario);
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
	
	public Funcionario buscarPorCod(Long codigo) {
		Funcionario f1 = null;
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.buscarPorCod");
			consulta.setLong("codigo", codigo); //"codigo" é referente a =:codigo em @NamedQuery
			f1 = (Funcionario) consulta.uniqueResult();  //retorna apenas uma unica consulta
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		return f1;
	}
	
	public void excluir(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(funcionario);
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
	
	public Funcionario autenticacao(String cpf, String senha) {
		Funcionario funcionario = null;
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.logar");
			consulta.setString("cpf", cpf);
			consulta.setString("senha", senha);
			funcionario = (Funcionario) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		}finally {
			sessao.close();
		}
		return funcionario;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
