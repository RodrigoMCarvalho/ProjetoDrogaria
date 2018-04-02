package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@SessionScoped   //existir� por todo tempo de sess�o do usu�rio
public class AutenticacaoBean {
	private Funcionario funcionarioLogado;

	
	public String autenticar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			funcionarioLogado = dao.autenticacao(funcionarioLogado.getCpf(), funcionarioLogado.getSenha());
			if (funcionarioLogado == null) {
				FacesUtil.addMsgError("Usu�rio ou/e senha inv�lidos!");
			} else {
			FacesUtil.addMsgInfo("Funcion�rio autenticado com sucesso");
			}
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro ao tentar acessar o sistema: "+e.getMessage());
		}
		return "/Pages/principal.xhtml?faces-redirect=true";
	}
	
	public String sair() {
		funcionarioLogado = null;
		return "/Pages/autenticacao.xhtml?faces-redirect=true";
	}

	
	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario(); //evitar NullPointException
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}


	
	
	
	
	
}
