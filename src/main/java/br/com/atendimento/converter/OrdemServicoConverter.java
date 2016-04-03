package br.com.atendimento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.atendimento.DAO.OrdemServicoDAO;
import br.com.atendimento.model.OrdemServico;

@FacesConverter(forClass=OrdemServico.class)
public class OrdemServicoConverter implements Converter {

	@Inject
	OrdemServicoDAO dao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		OrdemServico ordemServico = null;

		if (value != null && !value.isEmpty()) {
			Long id = new Long(value);
			ordemServico = dao.porID(id);
			return ordemServico;
		}

		return "";
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			OrdemServico ordemServico = (OrdemServico) value;
			return ordemServico.getCodigo() == null ? null : ordemServico.getCodigo().toString();
		}
		return "";
	}

}
