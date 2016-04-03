package br.com.atendimento.util.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.jdbc.Work;
import org.omnifaces.util.Messages;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ExecutorDeRelatorio implements Work {

	private String caminhoRelatorio;
	private HttpServletResponse response;
	private Map<String, Object> parametros;
	private String nomeArquivoSaida;

	private boolean relatorioGerado;

	public ExecutorDeRelatorio(String caminhoRelatorio, HttpServletResponse response, Map<String, Object> parametros,
			String nomeArquivoSaida) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.parametros = parametros;
		this.nomeArquivoSaida = nomeArquivoSaida;
		this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	@Override
	public void execute(Connection connection) throws SQLException {

		InputStream relatorioStream = getClass().getResourceAsStream(caminhoRelatorio);

		try {
			JasperPrint print = JasperFillManager.fillReport(relatorioStream, parametros, connection);

			relatorioGerado = print.getPages().size() > 0;

			if (relatorioGerado) {
				// response.setContentType("application/pdf");
				response.setContentType("application/xlsx");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + this.nomeArquivoSaida + "\"");

				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));				
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()) );
				
//				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
//				configuration.setCollapseRowSpan(true);
//				configuration.setAutoFitPageHeight(true);
//				exporter.setConfiguration(configuration);
				
				
				exporter.exportReport();
				

				//JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
			}

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao exibir o relatório");
			e.printStackTrace();
			throw new SQLException("Erro ao executar o relatorio " + caminhoRelatorio, e);
		}

	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}

}
