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
		//System.out.println(paginaAtual);    //mostra a página que o usuário está acessando no momento
		
		boolean paginaPublica = paginaAtual.contains("autenticacao.xhtml");
		//se a paginaAtual conter a sub string "autenticacao.xhtml, a variável paginaPublica será TRUE
		
		//System.out.println("É página pública ? " + paginaPublica);
		//Somente a página "autenticacao.xhtml" é uma página publica (TRUE), todas as demais são privadas (FALSE)
		
		if (!paginaPublica) { //SE não for página publica ("autenticacao.xhtml")
			ExternalContext externalContext = facesContext.getExternalContext();
			Map<String, Object> mapa = externalContext.getSessionMap(); // Mapa da sessão, contendo todas as váriáveis da aplicação
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) mapa.get("autenticacaoBean"); // capturar usuarioLogado
			//OBS: ManegedProperity somente é usado em classes Maneged Bean
			Funcionario funcionario = autenticacaoBean.getFuncionarioLogado();
			// System.out.println("Funcionario: " + funcionario); Mostra o funcionario logado no console
			
			// Se a função estiver como NULL ( acessar um página sem se autenticar
			if (funcionario.getFuncao() == null) {
				FacesUtil.addMsgError("Funcionário não autenticado!");
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
