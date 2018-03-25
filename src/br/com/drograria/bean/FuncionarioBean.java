package br.com.drograria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

//import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {
	
	private Funcionario funcionarioCadastro;
	
	public void salvar() {  //try catch receberá informações da Classe DAO
		try {
		FacesUtil.addMsgInfo("Funcionário cadastrado com sucesso!");  //***usando o GROWL e classe FacesUtil
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
//				"Informação: ", "Funcionário salvo com sucesso!")); //*** usando o Message ***
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro para salvar o funcionario: "+ e.getMessage());
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
	
}
