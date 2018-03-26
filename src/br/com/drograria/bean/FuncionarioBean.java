package br.com.drograria.bean;

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
	
	public void salvar() {  //try catch receberá informações da Classe DAO
		try {
		FacesUtil.addMsgInfo("Funcionário cadastrado com sucesso!");  //***usando o GROWL e classe FacesUtil
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
//				"Informação: ", "Funcionário salvo com sucesso!")); //*** usando o Message ***
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro para salvar o funcionario: "+ e.getMessage());
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

	public Funcionario getFuncionarioCadastro() {
		if (funcionarioCadastro==null) {
			funcionarioCadastro = new Funcionario();
			//necessário para evitar o erro "javax.el.PropertyNotFoundException:
			//Target Unreachable, [fabricanteCadastro] returned null"
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
