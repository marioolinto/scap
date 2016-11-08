package br.com.vitrinidatasoft.relatorios;

import br.com.vitrinidatasoft.model.Cliente;
import java.io.InputStream;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioClientes {
    
    public void gerarRelatorio(List<Cliente> clientes) throws JRException{
        InputStream fonte = this.getClass()
                .getResourceAsStream("/report/ClientReport.jrxml");   
        JasperReport report = JasperCompileManager.compileReport(fonte);
        JasperPrint print = JasperFillManager.fillReport(report, 
                null, new JRBeanCollectionDataSource(clientes));
        JasperViewer.viewReport(print, false);
    }
    
}
