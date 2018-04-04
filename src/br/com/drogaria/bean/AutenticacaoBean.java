package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@SessionScoped   //existirá por todo tempo de sessão do usuário
public class AutenticacaoBean {
	private Funcionario funcionarioLogado;

	
	public String autenticar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			funcionarioLogado = dao.autenticacao(funcionarioLogado.getCpf(),DigestUtils.md5Hex(funcionarioLogado.getSenha()));
			// necessário adicionar DigestUtil, por causa da senha no formato MD5
			if (funcionarioLogado == null) {
				FacesUtil.addMsgError("CPF ou/e senha inválidos!");
				return null;
			} else {
				FacesUtil.addMsgInfo("Funcionário autenticado com sucesso");
				return "/Pages/principal.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro ao tentar acessar o sistema: "+e.getMessage());
		}
		return null;
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
