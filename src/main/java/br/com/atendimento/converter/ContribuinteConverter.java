package br.com.atendimento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.atendimento.DAO.ContribuinteDAO;
import br.com.atendimento.model.Contribuinte;

@FacesConverter("contribuinteConverter")
public class ContribuinteConverter implements Converter {

	@Inject
	ContribuinteDAO dao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Contribuinte contribuinte = null;

		if (value != null && !value.isEmpty()) {
			
			Long id = new Long(value);			
			contribuinte = dao.porID(id);

			return contribuinte;
		}

		return "";
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Contribuinte contribuinte = (Contribuinte) value;

			return contribuinte.getCodigo() == null ? null : contribuinte.getCodigo().toString();
		}

		return "";
	}

}
