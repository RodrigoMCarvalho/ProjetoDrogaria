package br.com.drograria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

//import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {
	public void salvar() {
//		FacesUtil.addMsgInfo("Funcion�rio cadastrado com sucesso!");  ***usando o GROWL e classe FacesUtil
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Informa��o: ", "Funcion�rio salvo com sucesso!")); //*** usando o Message ***
	}
}
