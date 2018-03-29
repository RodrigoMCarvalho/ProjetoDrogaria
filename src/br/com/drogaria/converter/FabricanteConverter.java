package br.com.drogaria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

@FacesConverter("fabricanteConverter")
public class FabricanteConverter implements Converter {

	//Método para evitar o erro de validação.  É necessário ter o método EQUALS nas entidades
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try {
			Long codigo = Long.parseLong(valor); //converter para Long, igual o código do fabricante
			FabricanteDAO dao = new FabricanteDAO();
			Fabricante fabricante = dao.buscarPorCodigo(codigo);
			return fabricante;
		}catch (Exception e) {
			return null;
		}
	}

	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try {
			Fabricante fabricante = (Fabricante) objeto;  //casting de referências
			Long codigo = fabricante.getCodigo();
			return codigo.toString();
		} catch (Exception e) {
			return null;
		}
	}
}
