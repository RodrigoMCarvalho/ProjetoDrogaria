package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionarioCadastro;
	private List<Funcionario> listFuncionarios;
	private List<Funcionario> listFuncionariosFiltrados;
	private String acao; //irá guardar 'Novo', 'Editar', 'Excluir'
	private Long codigo;

	public void salvar() { // try catch receberá informações da Classe DAO
		try {
			
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.salvar(funcionarioCadastro);
			FacesUtil.addMsgInfo("Funcionário cadastrado com sucesso!"); // ***usando o GROWL e classe FacesUtil
			funcionarioCadastro = new Funcionario();
			// FacesContext.getCurrentInstance().addMessage(null, new
			// FacesMessage(FacesMessage.SEVERITY_INFO,
			// "Informação: ", "Funcionário salvo com sucesso!")); //*** usando o Message
			// ***
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro para salvar o funcionario: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.excluir(funcionarioCadastro);
			FacesUtil.addMsgInfo("Funcionário excluído com sucesso!");
			funcionarioCadastro = new Funcionario();
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro para excluir o funcionário: " + e.getMessage());
		}
	}
	
	public void editar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.editar(funcionarioCadastro);
			FacesUtil.addMsgInfo("Funcionário alterado com sucesso!");
			funcionarioCadastro = new Funcionario();
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro para editar o funcionário: " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			listFuncionarios = fdao.listar();
		} catch (Exception e) {
			FacesUtil.addMsgError("Falha ao carregar lista de funcionarios: " + e.getMessage());
		}
	}
	
	public void carregarCadastro() {
		try {
			if (codigo != null) {
				FuncionarioDAO dao = new FuncionarioDAO();
				funcionarioCadastro = dao.buscarPorCod(codigo);
			} else {
				funcionarioCadastro = new Funcionario();
			}
		} catch (Exception e) {
			FacesUtil.addMsgError("Falha ao tentar carregar o funcionário: " + e.getMessage());
		}
	}
	
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Funcionario getFuncionarioCadastro() {
		if (funcionarioCadastro == null) {
			funcionarioCadastro = new Funcionario();
			// necessário para evitar o erro "javax.el.PropertyNotFoundException:
			// Target Unreachable, [fabricanteCadastro] returned null"
		}
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public List<Funcionario> getListFuncionarios() {
		return listFuncionarios;
	}

	public void setListFuncionarios(List<Funcionario> listFuncionarios) {
		this.listFuncionarios = listFuncionarios;
	}

	public List<Funcionario> getListFuncionariosFiltrados() {
		return listFuncionariosFiltrados;
	}

	public void setListFuncionariosFiltrados(List<Funcionario> listFuncionariosFiltrados) {
		this.listFuncionariosFiltrados = listFuncionariosFiltrados;
	}

}
