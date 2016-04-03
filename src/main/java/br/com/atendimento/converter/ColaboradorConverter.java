package br.com.atendimento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.atendimento.DAO.ColaboradorDAO;
import br.com.atendimento.model.Colaborador;

@FacesConverter(forClass = Colaborador.class)
public class ColaboradorConverter implements Converter {

	@Inject
	ColaboradorDAO dao = null;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Colaborador colaborador = null;

		if (value != null && !value.isEmpty()) {
			Long id = new Long(value);
			colaborador = dao.porID(id);
			return colaborador;
		}

		return "";
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Colaborador colaborador = (Colaborador) value;
			return colaborador.getCodigo() == null ? null : colaborador.getCodigo().toString();
		}
		return "";
	}

}
