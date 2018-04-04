package br.com.drogaria.util;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.drogaria.bean.AutenticacaoBean;
import br.com.drogaria.domain.Funcionario;

@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext  = event.getFacesContext();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		String paginaAtual = uiViewRoot.getViewId();
		//System.out.println(paginaAtual);    //mostra a p�gina que o usu�rio est� acessando no momento
		
		boolean paginaPublica = paginaAtual.contains("autenticacao.xhtml");
		//se a paginaAtual conter a sub string "autenticacao.xhtml, a vari�vel paginaPublica ser� TRUE
		
		//System.out.println("� p�gina p�blica ? " + paginaPublica);
		//Somente a p�gina "autenticacao.xhtml" � uma p�gina publica (TRUE), todas as demais s�o privadas (FALSE)
		
		if (!paginaPublica) { //SE n�o for p�gina publica ("autenticacao.xhtml")
			ExternalContext externalContext = facesContext.getExternalContext();
			Map<String, Object> mapa = externalContext.getSessionMap(); // Mapa da sess�o, contendo todas as v�ri�veis da aplica��o
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) mapa.get("autenticacaoBean"); // capturar usuarioLogado
			//OBS: ManegedProperity somente � usado em classes Maneged Bean
			Funcionario funcionario = autenticacaoBean.getFuncionarioLogado();
			// System.out.println("Funcionario: " + funcionario); Mostra o funcionario logado no console
			
			// Se a fun��o estiver como NULL ( acessar um p�gina sem se autenticar
			if (funcionario.getFuncao() == null) {
				FacesUtil.addMsgError("Funcion�rio n�o autenticado!");
				Application application = facesContext.getApplication();
				NavigationHandler navigationHandler = application.getNavigationHandler();
				navigationHandler.handleNavigation(facesContext, null, "/Pages/autenticacao.xhtml?faces-redirect=true");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
