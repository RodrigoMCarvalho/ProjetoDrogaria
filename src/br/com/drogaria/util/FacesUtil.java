package br.com.drogaria.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {
	public static void addMsgInfo(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		//capturar o contexto do JSF
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
	}
	
	public static void addMsgError(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		//capturar o contexto do JSF
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
	}
	
}
