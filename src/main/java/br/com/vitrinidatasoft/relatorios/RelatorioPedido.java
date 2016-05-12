/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.relatorios;

import br.com.vitrinidatasoft.model.Pedido;
import java.io.InputStream;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mrhell
 */
public class RelatorioPedido {
    public void gerarRelatorio(List<Pedido> pedidos) throws JRException{
        InputStream fonte = this.getClass()
                .getResourceAsStream("/report/Pedido.jrxml");   
        JasperReport report = JasperCompileManager.compileReport(fonte);
        JasperPrint print = JasperFillManager.fillReport(report, 
                null, new JRBeanCollectionDataSource(pedidos));
        JasperViewer.viewReport(print, false);
    }
    
}
