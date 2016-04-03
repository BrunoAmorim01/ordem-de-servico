package br.com.atendimento.bean.relatorios;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.omnifaces.util.Messages;

import br.com.atendimento.util.report.ExecutorDeRelatorio;

@SuppressWarnings("serial")
@Named
@RequestScoped
public class RelatorioOrdemServicoEmitida implements Serializable {	
	
	@NotNull
	private Date dataInicio;
	@NotNull
	private Date dataFim;
	
	@Inject
	private EntityManager manager;
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("dataInicial", dataInicio);
		parametros.put("dataFim", dataFim);

		ExecutorDeRelatorio executor = new ExecutorDeRelatorio("/relatorios/relatorioOrdemServico.jasper", response,
				parametros, "Ordemdeservico.xlsx");
		
		Session session= manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			Messages.addGlobalError("A execução do relatório não retornou dados");
		}
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}
