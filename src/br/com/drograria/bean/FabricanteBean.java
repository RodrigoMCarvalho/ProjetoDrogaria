package br.com.drograria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.FacesUtil;


@ManagedBean
@ViewScoped
public class FabricanteBean {

	private Fabricante fabricanteCadastro;
	private List<Fabricante> listFabricantes;
	private List<Fabricante> listFabricantesFiltrados;

	public void salvar() { // try catch receberá informações da classe DAO
		try {
			FabricanteDAO fdao = new FabricanteDAO();
		
			fdao.salvar(fabricanteCadastro);
			fabricanteCadastro = new Fabricante();//reinstanciar para limpar os dados após a inserção
			FacesUtil.addMsgInfo("Fabricante salvo com sucesso!"); // ***usando o GROWL e classe FacesUtil ***
			// FacesContext.getCurrentInstance().addMessage(null, new
			// FacesMessage(FacesMessage.SEVERITY_INFO,
			// "Informação: ", "Fabricante salvo com sucesso!")); //*** usando o Message ***
		} catch (Exception e) {
			// e.printStackTrace(); ***usado para debugar***
			FacesUtil.addMsgError("Erro para salvar um fabricante: " + e.getMessage());
		}
	}
	
	public void carregarPesquisa() {
		try {
			FabricanteDAO fdao = new FabricanteDAO();
			listFabricantes = fdao.listar();
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro para listar os fabricantes: " +e.getMessage());
		}
	}
	
	public void carregarCadastro() {
		try {
			String valor = FacesUtil.getParam("fabCod"); //parâmetro enviado por GET
			if (valor != null) {
				Long codigo =Long.parseLong(valor); //converter valor para Long
				FabricanteDAO fdao = new FabricanteDAO();
				fabricanteCadastro = fdao.buscarPorCodigo(codigo);
			}
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro ao tentar obter os dados do fabricantes: " +e.getMessage());
		}
	}
	
	public void novo() {
		fabricanteCadastro = new Fabricante(); ////reinstanciar para limpar os dados após a inserção
	}

	public Fabricante getFabricanteCadastro() {
		if (fabricanteCadastro == null) {
			fabricanteCadastro = new Fabricante();
			// necessário para evitar o erro "javax.el.PropertyNotFoundException:
			// Target Unreachable, [fabricanteCadastro] returned null"
		}
		return fabricanteCadastro;
	}

	public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
	}

	public List<Fabricante> getListFabricantes() {
		return listFabricantes;
	}

	public void setListFabricantes(List<Fabricante> listFabricantes) {
		this.listFabricantes = listFabricantes;
	}

	public List<Fabricante> getListFabricantesFiltrados() {
		return listFabricantesFiltrados;
	}

	public void setListFabricantesFiltrados(List<Fabricante> listFabricantesFiltrados) {
		this.listFabricantesFiltrados = listFabricantesFiltrados;
	}

}
